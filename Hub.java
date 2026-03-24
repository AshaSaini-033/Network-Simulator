public class Hub extends Device {

    public Hub(String name) {
        super(name);
    }

    @Override
    public void receive(Frame frame, Device from) {
        System.out.println(name + " broadcasting...");

        for (Device d : connections) {
            if (d != from) {
                d.receive(frame, this);
            }
        }
    }
}