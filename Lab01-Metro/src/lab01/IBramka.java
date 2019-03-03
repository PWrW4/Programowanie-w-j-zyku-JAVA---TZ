package lab01;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IBramka extends Remote {

    // do pobierania informacji o statystyce – do wykorzystania przez Monitor
    // statystyka to tablica z dwiema wartościami: liczba wejść, liczba wyjść
    public int[] getStatystyka(Date pocz, Date kon) throws RemoteException;
    // do zdalnego zatrzymywania bramek
    public boolean zamknijBramke() throws RemoteException;
    // do pobrania numeru bramki
    public int getNumer() throws RemoteException;

}
