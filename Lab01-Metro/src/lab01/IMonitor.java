package lab01;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IMonitor extends Remote {

    public void koniecznaAktualizacja() throws RemoteException;

}
