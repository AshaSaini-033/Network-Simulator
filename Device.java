import java.util.*;

public abstract class Device {
    String name;
    List<Device> connections = new ArrayList<>();

    public Device(String name) {
        this.name = name;
    }

    public void connect(Device device) {
        connections.add(device);
    }

    public abstract void receive(Frame frame, Device from);
}