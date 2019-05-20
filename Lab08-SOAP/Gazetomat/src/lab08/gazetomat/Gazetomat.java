package lab08.gazetomat;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface Gazetomat {
    @WebMethod
    public int getId();

    @WebMethod
    public List<Paper> getItems();
}