public class Hub extends Device {

    public Hub(String name) {
        super(name); //to pass name up to parent device 
    }

    @Override // compiler check we actully matched the parent method signature
    public void receive(Frame frame, Device from) {
        System.out.println(name + " broadcasting..."); //dumb nature 

        for (Device d : conn) { //all connected device mm send data 
            if (d != from) { //to prevent looop back to sender
                d.receive(frame, this); //FORWARDING THE FRAME TO ALL OTHER DEVICES EXCEPT THE SENDER
            }
        }
    }
}