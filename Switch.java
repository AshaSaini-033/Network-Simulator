import java.util.*;

public class Switch extends Device {

    Map<String, Device> macTable = new HashMap<>();// create a lookup tasbel  is noit use it the it willl act like hubb

    public Switch(String name) {
        super(name);
    }
   
    @Override
public void receive(Frame frame, Device from) {
    // 1. Learn source (Always lowercase to avoid mismatches)
    macTable.put(frame.source.toLowerCase(), from);

    System.out.println(name + " learned: " + frame.source);

    // 2. Search for destination (Always lowercase the search key)
    String dest = frame.destination.toLowerCase(); 
    
    if (macTable.containsKey(dest)) {
        Device target = macTable.get(dest);
        System.out.println(name + " forwarding to " + frame.destination);
        target.receive(frame, this);
    } else {
        System.out.println(name + " broadcasting (unknown destination)");
        for (Device d : conn) {
            if (d != from) {
                d.receive(frame, this);
            }
        }
    }
}
}