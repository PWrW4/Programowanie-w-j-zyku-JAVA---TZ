package lab08.gazetomat;

import lab08.centrala.Centrala;
import lab08.centrala.CentralaImplService;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

@WebService(endpointInterface = "lab08.gazetomat.Gazetomat")
public class GazetomatImpl implements Gazetomat, Runnable {

    int ID = 1;
    List<Paper> papers = new ArrayList<Paper>();
    Centrala centrala;

    public GazetomatImpl() {
        papers.add(new Paper(10,"Wyborcza"));
        papers.add(new Paper(20,"Dziennik śląski"));
        papers.add(new Paper(30,"Bravo Girl"));

        Thread thread = new Thread(this);
        thread.start();

        CentralaImplService service = null;
        try {
            service = new CentralaImplService(new URL("http://localhost:8080/WS/Centrala?wsdl"));
            centrala = service.getCentralaImplPort();
            ID = centrala.getFreePort();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void register() {
        System.out.println("Uruchomiono automat na porcie: " + ID);
        Endpoint.publish("http://localhost:"+ID+"/WS/Automat", this);
        centrala.registerAutomat(ID);
    }

    public void unregister() {
        System.out.println("Wyrejestrowano");
        centrala.unregisterAutomat(ID);
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setpapers(List<Paper> papers) {
        this.papers = papers;
    }

    private void changeValues() throws InterruptedException {
        for (Paper paper: papers) {
            paper.setCount(new Random().nextInt(100));
        }
        sleep(10000);
        centrala.updateRequest(ID);
    }

    @Override
    public int getId() {
        return ID;
    }

    @Override
    public List<Paper> getItems() {
        return papers;
    }

    @Override
    public void run() {
        try {
            while (true) {
                changeValues();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
