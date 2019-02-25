package lab01;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ICentrala extends Remote {

    // do rejestrowania bramki w systemie, wynik – numer przydzielony bramce (bramka musi wpisać sobie ten numer po wywołaniu zdalnym) lub wartość ujemna, gdy operacja nieudana.
    public int zarejestrujBramke(Object bramka) throws RemoteException;
    // do wyrejestrowywania bramek
    public boolean wyrejestrujBramke(int nrBramki) throws RemoteException;
    // do pobierania listy bramek aktywnych (w celu monitorowania ich "aktywności")
    public ArrayList<Object> getZarejestrowaneBramki() throws RemoteException;
    // do rejestracji w centrali namiastki monitora (w celu późniejszego informowania o konieczności aktualizacji)
    public void zarejestrujMonitor(Object o)  throws RemoteException;
    public void wyrejestrujMonitor()  throws RemoteException;
    public Object getMonitor() throws RemoteException;

}
