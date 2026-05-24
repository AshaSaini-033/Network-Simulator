public class FileService {

    public void transferFile(
            EndDevice a,
            EndDevice b,
            String file) {

        System.out.println(
                "\nfile transfer..."
        );

        a.sendSegment(
                5050,
                6060,
                file,
                b
        );
    }
}