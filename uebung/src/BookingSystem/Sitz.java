package BookingSystem;

/**
 * Created by mincekara on 01.10.2015.
 */
public class Sitz {
    private int reihe;
    private char position;
    private Buchungsstatus status;

    public Sitz(int reihe, char position){
        this.reihe=reihe;
        this.position=position;
    }

    public int getReihe() {
        return reihe;
    }

    public char getPosition() {
        return position;
    }

    public Buchungsstatus getStatus() {
        return status;
    }

    public void setReihe(int reihe) {
        this.reihe = reihe;
    }

    public void setPosition(char position) {
        this.position = position;
    }

    public void setStatus(Buchungsstatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "" + reihe + position;
    }
}
