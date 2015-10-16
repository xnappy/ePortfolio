package BookingSystem;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mincekara on 01.10.2015.
 */
public class SitzButton extends JButton {

    private Sitz sitz;

    public SitzButton(Sitz s){
        super(""+s.toString()); //oder: "" + s.getReihe + s.getPosition
        this.sitz = s;
        sitz.setStatus(Buchungsstatus.FREI);
        buttonStatus();
    }

    public void buttonStatus(){
        if (sitz.getStatus()==Buchungsstatus.FREI){
            setBackground(Color.WHITE);
            setEnabled(true);
        } if (sitz.getStatus()==Buchungsstatus.PENDING){
            setBackground(Color.RED);
            setEnabled(true);
        } if (sitz.getStatus()==Buchungsstatus.GEBUCHT){
            setBackground(Color.WHITE);
            setEnabled(false);
        }
    }

    public Sitz getSitz() {
        return sitz;
    }

    public void setSitz(Sitz sitz) {
        this.sitz = sitz;
    }
}
