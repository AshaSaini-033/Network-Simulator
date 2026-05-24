import java.util.*; //use  list and arraylist 

public abstract class Device { //device must be specific 
    String name; //pc 1 pc2 
    List<Device> conn = new ArrayList<>(); //create list of device 

    public Device(String name) {
        this.name = name; //otherwise The device name would remain null.
    }

    public void connect(Device device) {
        conn.add(device);
    }

    public abstract void receive(Frame frame, Device from);//abstract method to be implemented by subclasses because every device may have different way to receive data
    // from parameter allows the device to identify the sender and exclude them during broadcasting or forwarding. loop creation is avoided by not sending the frame back to the sender.
}