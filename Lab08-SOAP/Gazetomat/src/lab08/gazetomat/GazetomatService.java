package lab08.gazetomat;

import java.io.IOException;

public class GazetomatService {

    public static void main(String[] args) throws IOException {
        GazetomatImpl automat = new GazetomatImpl();
        automat.register();
        System.in.read();
        automat.unregister();
    }

}
