
package lab08.centrala;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CentralaImplService", targetNamespace = "http://centrala.lab08/", wsdlLocation = "http://localhost:8080/WS/Centrala?wsdl")
public class CentralaImplService
    extends Service
{

    private final static URL CENTRALAIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException CENTRALAIMPLSERVICE_EXCEPTION;
    private final static QName CENTRALAIMPLSERVICE_QNAME = new QName("http://centrala.lab08/", "CentralaImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/WS/Centrala?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CENTRALAIMPLSERVICE_WSDL_LOCATION = url;
        CENTRALAIMPLSERVICE_EXCEPTION = e;
    }

    public CentralaImplService() {
        super(__getWsdlLocation(), CENTRALAIMPLSERVICE_QNAME);
    }

    public CentralaImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CENTRALAIMPLSERVICE_QNAME, features);
    }

    public CentralaImplService(URL wsdlLocation) {
        super(wsdlLocation, CENTRALAIMPLSERVICE_QNAME);
    }

    public CentralaImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CENTRALAIMPLSERVICE_QNAME, features);
    }

    public CentralaImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CentralaImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Centrala
     */
    @WebEndpoint(name = "CentralaImplPort")
    public Centrala getCentralaImplPort() {
        return super.getPort(new QName("http://centrala.lab08/", "CentralaImplPort"), Centrala.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Centrala
     */
    @WebEndpoint(name = "CentralaImplPort")
    public Centrala getCentralaImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://centrala.lab08/", "CentralaImplPort"), Centrala.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CENTRALAIMPLSERVICE_EXCEPTION!= null) {
            throw CENTRALAIMPLSERVICE_EXCEPTION;
        }
        return CENTRALAIMPLSERVICE_WSDL_LOCATION;
    }

}
