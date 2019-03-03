package lab01;

import java.io.Serializable;

public class Bramka implements Serializable {

    private int nr;
    private int wekscia;
    private int wyjscia;

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getWekscia() {
        return wekscia;
    }

    public void setWekscia(int wekscia) {
        this.wekscia = wekscia;
    }

    public int getWyjscia() {
        return wyjscia;
    }

    public void setWyjscia(int wyjscia) {
        this.wyjscia = wyjscia;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private boolean running;

    public Bramka(int nr, int wekscia, int wyjscia, boolean running) {
        this.nr = nr;
        this.wekscia = wekscia;
        this.wyjscia = wyjscia;
        this.running = running;
    }
}
