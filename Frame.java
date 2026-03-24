public class Frame {
    String source;
    String destination;
    String data;
    int parity;

    public Frame(String source, String destination, String data) {
        this.source = source;
        this.destination = destination;
        this.data = data;
        this.parity = calculateParity(data);
    }

    private int calculateParity(String data) {
        int count = 0;
        for (char c : data.toCharArray()) {
            if (c == '1') count++;
        }
        return count % 2; // even parity
    }

    public boolean checkError() {
        int count = 0;
        for (char c : data.toCharArray()) {
            if (c == '1') count++;
        }
        return (count % 2) == parity;
    }
}