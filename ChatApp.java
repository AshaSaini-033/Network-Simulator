public class ChatApp {

    public void sendMessage(
            EndDevice a,
            EndDevice b,
            String msg) {

        System.out.println(
                "\nchat application..."
        );

        a.sendSegment(
                5000,
                6000,
                msg,
                b
        );
    }
}