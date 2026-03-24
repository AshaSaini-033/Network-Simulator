public class Bridge extends Device {

    public Bridge(String name) {
        super(name);
    }

    @Override
    public void receive(Frame frame, Device from) {
        System.out.println(name + " forwarding frame");

        for (Device d : connections) {
            if (d != from) {
                d.receive(frame, this);
            }
        }
    }
}