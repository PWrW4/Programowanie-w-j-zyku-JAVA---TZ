package lab13;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.swing.*;

public class AppClient {

    public AppClient() {
        try {

            //int port = Integer.parseInt(JOptionPane.showInputDialog("port:"));

            String servelUrl = "service:jmx:rmi:///jndi/rmi://localhost:"+2005+"/jmxrmi";
            JMXServiceURL url = new JMXServiceURL(servelUrl);

            JMXConnector con = JMXConnectorFactory.connect(url);
            MBeanServerConnection server = con.getMBeanServerConnection();
            ObjectName name = new ObjectName("lab13.Advertisement:type=Controller,name=BannerController");

            BannerControllerMBean mbean = (BannerControllerMBean) MBeanServerInvocationHandler.
                    newProxyInstance(server,
                            name,
                            BannerControllerMBean.class,
                            false);

            mbean.Start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AppClient client = new AppClient();
    }

}
