package lab08.centrala;

import lab08.gazetomat.Gazetomat;
import lab08.gazetomat.GazetomatImplService;
import lab08.gazetomat.Paper;

import javax.jws.WebService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface= "lab08.centrala.Centrala")
public class CentralaImpl implements Centrala {


    private static int freePort = 8080;
    public List<Gazetomat> gazetomats = new ArrayList<Gazetomat>();

    @Override
    public int getFreePort() {
        freePort++;
        return freePort;
    }

    @Override
    public void registerAutomat(int port) {
        GazetomatImplService service = null;
        String url = "http://localhost:"+port+"/WS/Automat?wsdl";
        try {
            service = new GazetomatImplService(new URL(url));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assert service != null;
        Gazetomat automat = service.getGazetomatImplPort();
        gazetomats.add(automat);
    }

    @Override
    public void unregisterAutomat(int port) {
        for (Gazetomat automat: gazetomats) {
            if(automat.getId() == port) {
                gazetomats.remove(automat);
            }
            System.out.println(gazetomats);
        }
    }

    @Override
    public void updateRequest(int port) {
        for (Gazetomat automat : gazetomats) {
            if(automat.getId() == port) {
                List<Paper> items = automat.getItems();
                System.out.println("\nGazetomat nr " + automat.getId());
                for (Paper drink : items) {
                    System.out.println(drink.getName() + " : " + drink.getCount());
                }
            }
        }
    }

}
