import java.util.*;

public class Main {

    // 🔹 Test 1: Direct Connection
    public static void testDirectConnection() {
        System.out.println("\n--- TEST 1: Direct Connection ---");

        EndDevice pc1 = new EndDevice("PC1", "192.168.1.1");
        EndDevice pc2 = new EndDevice("PC2", "192.168.1.2");

        pc1.connect(pc2);
        pc2.connect(pc1);

        pc1.send("PC2", "Hello Direct Connection");
    }

    // 🔹 Test 2: Star Topology using Hub
    public static void testStarWithHub() {
        System.out.println("\n--- TEST 2: Star Topology using HUB ---");

        Hub hub = new Hub("Hub1");
        EndDevice[] pcs = new EndDevice[5];

        for (int i = 0; i < 5; i++) {
            pcs[i] = new EndDevice("PC" + (i + 1), "192.168.1." + (i + 1));
            pcs[i].connect(hub);
            hub.connect(pcs[i]);
        }

        pcs[0].send("PC3", "Hello via Hub");

        System.out.println("\nBroadcast Domains: 1");
        System.out.println("Collision Domains: 1");
    }

    // 🔹 Test 3: Star Topology using Switch
    public static void testStarWithSwitch() {
        System.out.println("\n--- TEST 3: Star Topology using SWITCH ---");

        Switch sw = new Switch("Switch1");
        EndDevice[] pcs = new EndDevice[5];

        for (int i = 0; i < 5; i++) {
            pcs[i] = new EndDevice("PC" + (i + 1), "192.168.1." + (i + 1));
            pcs[i].connect(sw);
            sw.connect(pcs[i]);
        }

        pcs[0].send("PC3", "First Message");
        pcs[0].send("PC3", "Second Message");

        System.out.println("\nBroadcast Domains: 1");
        System.out.println("Collision Domains: 5");
    }

    // 🔹 Test 4: Two Hubs + Switch
    public static void testTwoHubsWithSwitch() {
        System.out.println("\n--- TEST 4: Two Hubs connected via Switch ---");

        Hub hub1 = new Hub("Hub1");
        Hub hub2 = new Hub("Hub2");
        Switch sw = new Switch("Switch1");

        // connect hubs to switch
        hub1.connect(sw);
        hub2.connect(sw);
        sw.connect(hub1);
        sw.connect(hub2);

        EndDevice[] pcs1 = new EndDevice[5];
        EndDevice[] pcs2 = new EndDevice[5];

        // hub1 devices
        for (int i = 0; i < 5; i++) {
            pcs1[i] = new EndDevice("H1_PC" + (i + 1), "10.0.0." + (i + 1));
            pcs1[i].connect(hub1);
            hub1.connect(pcs1[i]);
        }

        // hub2 devices
        for (int i = 0; i < 5; i++) {
            pcs2[i] = new EndDevice("H2_PC" + (i + 1), "20.0.0." + (i + 1));
            pcs2[i].connect(hub2);
            hub2.connect(pcs2[i]);
        }

        pcs1[0].send("H2_PC3", "Hello from Hub1 network");

        System.out.println("\nBroadcast Domains: 1");
        System.out.println("Collision Domains: 3");
    }

    // 🔹 Test 5: Error Control
    public static void testErrorControl() {
        System.out.println("\n--- TEST 5: ERROR CONTROL ---");

        EndDevice pc1 = new EndDevice("PC1", "192.168.1.1");
        EndDevice pc2 = new EndDevice("PC2", "192.168.1.2");

        pc1.connect(pc2);
        pc2.connect(pc1);

        // Correct data
        pc1.send("PC2", "101010");

        // Faulty frame
        Frame faulty = new Frame("PC1", "PC2", "111111");
        faulty.parity = 0; // force wrong parity

        pc2.receive(faulty, pc1);
    }

    // 🔹 Test 6: Sliding Window
    public static void testSlidingWindow() {
        System.out.println("\n--- TEST 6: SLIDING WINDOW ---");

        EndDevice pc1 = new EndDevice("PC1", "192.168.1.1");
        EndDevice pc2 = new EndDevice("PC2", "192.168.1.2");

        pc1.connect(pc2);
        pc2.connect(pc1);

        pc1.sendWithSlidingWindow("PC2", "HELLOWORLD");
    }

    // 🔹 MAIN MENU
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== NETWORK SIMULATOR =====");
            System.out.println("1. Direct Connection");
            System.out.println("2. Star Topology (Hub)");
            System.out.println("3. Star Topology (Switch)");
            System.out.println("4. Two Hubs + Switch");
            System.out.println("5. Error Control");
            System.out.println("6. Sliding Window");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    testDirectConnection();
                    break;
                case 2:
                    testStarWithHub();
                    break;
                case 3:
                    testStarWithSwitch();
                    break;
                case 4:
                    testTwoHubsWithSwitch();
                    break;
                case 5:
                    testErrorControl();
                    break;
                case 6:
                    testSlidingWindow();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}