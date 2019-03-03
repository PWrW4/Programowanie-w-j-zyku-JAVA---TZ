package lab01;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface ICentrala extends Remote {

    // do rejestrowania bramki w systemie, wynik – numer przydzielony bramce (bramka musi wpisać sobie ten numer po wywołaniu zdalnym) lub wartość ujemna, gdy operacja nieudana.
    public int zarejestrujBramke(IBramka bramka) throws RemoteException;
    // do wyrejestrowywania bramek
    public boolean wyrejestrujBramke(int nrBramki) throws RemoteException;
    // do pobierania listy bramek aktywnych (w celu monitorowania ich "aktywności")
    public Map<Integer, IBramka> getZarejestrowaneBramki() throws RemoteException;
    // do rejestracji w centrali namiastki monitora (w celu późniejszego informowania o konieczności aktualizacji)
    public void zarejestrujMonitor(IMonitor o)  throws RemoteException;
    public void wyrejestrujMonitor()  throws RemoteException;
}
