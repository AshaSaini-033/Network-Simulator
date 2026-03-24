import java.util.*;

public class Switch extends Device {

    Map<String, Device> macTable = new HashMap<>();

    public Switch(String name) {
        super(name);
    }

    @Override
    public void receive(Frame frame, Device from) {

        // Learn source
        macTable.put(frame.source, from);

        System.out.println(name + " learned: " + frame.source);

        // Forward if known
        if (macTable.containsKey(frame.destination)) {
            Device target = macTable.get(frame.destination);
            System.out.println(name + " forwarding to " + frame.destination);
            target.receive(frame, this);
        } else {
            System.out.println(name + " broadcasting (unknown destination)");
            for (Device d : connections) {
                if (d != from) {
                    d.receive(frame, this);
                }
            }
        }
    }
}