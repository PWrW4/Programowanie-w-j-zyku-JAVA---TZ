package lab08.centrala;

import javax.xml.ws.Endpoint;

public class CentralaService {


    private CentralaImpl centralaImpl;

    public CentralaService() {
        centralaImpl = new CentralaImpl();
        Endpoint.publish("http://localhost:8080/WS/Centrala", centralaImpl);
    }

    public static void main(String[] args) {
        CentralaService service = new CentralaService();
        System.out.println("service up");
    }

}
