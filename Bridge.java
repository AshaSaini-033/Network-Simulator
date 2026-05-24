public class Bridge extends Device {

    public Bridge(String name) {
        super(name);
    }

    @Override
    public void receive(Frame frame, Device from) {
        System.out.println(name + " forwarding frame"); //To determine what happens when data hits the bridge.

        for (Device d : conn) {
            if (d != from) {
                d.receive(frame, this);
            }
        }
    }
}