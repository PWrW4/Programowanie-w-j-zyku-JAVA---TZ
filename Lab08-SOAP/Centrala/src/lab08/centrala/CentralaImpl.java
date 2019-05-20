package lab08.centrala;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface= "lab08.centrala.Centrala")
public class CentralaImpl implements Centrala {


    private static int freePort = 8080;
    //public List<Paper> automats = new ArrayList<Paper>();

    @Override
    public int getFreePort() {
        freePort++;
        return freePort;
    }

    @Override
    public void registerAutomat(int port) {
    }

    @Override
    public void unregisterAutomat(int port) {

    }

    @Override
    public void updateRequest(int port) {

    }

}
