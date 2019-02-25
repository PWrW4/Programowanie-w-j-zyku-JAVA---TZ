package lab01;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBramka extends Remote {

    public void koniecznaAktualizacja() throws RemoteException;

}
