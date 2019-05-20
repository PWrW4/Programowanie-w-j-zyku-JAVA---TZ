package lab08.centrala;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Centrala {

    @WebMethod
    public int getFreePort();

    @WebMethod
    public void registerAutomat(int port);

    @WebMethod
    public void unregisterAutomat(int port);

    @WebMethod
    public void updateRequest(int port);
}
