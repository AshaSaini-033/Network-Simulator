public class Frame {
    String source;
    String destination;
    String data;
    int parity;

    public Frame(String source, String destination, String data) { //frame.	To "encapsulate" the data and headers into a single unit.
        this.source = source;
        this.destination = destination;
        this.data = data;
        this.parity = calculateParity(data);
}
         //generate the Checksum (parity bit) for error control. It counts the number of 1s in the data and sets parity to 0 for even count and 1 for odd count.

    private int calculateParity(String data) {
        int count = 0;//bitr counting 
        for (char c : data.toCharArray()) { //Loops through each character.
            if (c == '1') count++;
        }
        return count % 2; // even parity
    }

    public boolean checkError() { //reveverr side varification //if not use ...recever acept corrupted data 
        int count = 0;
        for (char c : data.toCharArray()) {
            if (c == '1') count++;
        }
        return (count % 2) == parity; //parity-> recevied , comapre to calculated 
    }
}