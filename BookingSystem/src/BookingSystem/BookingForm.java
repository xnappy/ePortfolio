package BookingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * Created by mincekara on 01.10.2015.
 */
public class BookingForm {
    private Flug flug;

    private JFrame fenster;
    private JPanel top;
    private JPanel center;
    private JPanel bot;
    private JButton buchen;
    private JButton verwerfen;
    private String txtfile="bookings.txt";

    private JLabel timer = new JLabel();
    UhrzeitThread uhr = new UhrzeitThread();

    public BookingForm(Flug flug){
        this.flug = flug;
        createGUI();
    }

    public void createGUI() {
        uhr.start();
        fenster = new JFrame();
        fenster.setLayout(new BorderLayout(10, 10));
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setSize(450, 600);
        fenster.setTitle("Buchung für Flug " + flug.getName());

        top = new JPanel(new GridLayout(5, 2, 10, 10));
        top.add(new JLabel("Fluggesellschaft"));
        top.add(new JLabel(flug.getAirlane().getName() + flug.getAirlane().getCode()));
        top.add(new JLabel("von:"));
        top.add(new JLabel(flug.getFrom().getName() + flug.getFrom().getCode()));
        top.add(new JLabel("nach:"));
        top.add(new JLabel(flug.getTo().getName() + flug.getTo().getCode()));
        top.add(new JLabel("freie Plätze:"));
        top.add(new JLabel("" + flug.freiePlaetze()));
        top.add(new JLabel("Zeit:"));
        top.add(timer);

        center = new JPanel(new GridLayout(flug.getRows(), flug.getSeats(), 5, 5));

        ActionListener al = new ActionListener() { //Auslagern des Actionlisteners, damit nicht i*j viele ActionListener erstellt werden
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SitzButton self = (SitzButton) actionEvent.getSource();

                if (self.getSitz().getStatus() == Buchungsstatus.FREI) {
                    self.getSitz().setStatus(Buchungsstatus.PENDING);
                    self.buttonStatus();
                } else {
                    if (self.getSitz().getStatus() == Buchungsstatus.PENDING) {
                        self.getSitz().setStatus(Buchungsstatus.FREI);
                        self.buttonStatus();
                    }
                }
            }
        };

        final SitzButton[][] sb = new SitzButton[flug.getRows()][flug.getSeats()];

        for (int i = 0; i < flug.getRows(); i++) {
            for (int j = 0; j < flug.getSeats(); j++) {
                sb[i][j] = new SitzButton(flug.getSitze()[i][j]);
                sb[i][j].addActionListener(al);
                center.add(sb[i][j]);
            }
        }

        bot = new JPanel(new GridLayout(1, 3));
        buchen = new JButton("Buchen");
        verwerfen = new JButton("Verwerfen");
        bot.add(buchen);
        bot.add(verwerfen);

        buchen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (int i = 0; i < flug.getRows(); i++) {
                    for (int j = 0; j < flug.getSeats(); j++) {
                        if (sb[i][j].getSitz().getStatus() == Buchungsstatus.PENDING) {
                            sb[i][j].getSitz().setStatus(Buchungsstatus.GEBUCHT);
                            sb[i][j].buttonStatus();
                            logBooking(sb[i][j]);
                        }
                    }
                }
            }
        });

        verwerfen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (int i = 0; i < flug.getRows(); i++) {
                    for (int j = 0; j < flug.getSeats(); j++)
                        if (sb[i][j].getSitz().getStatus() == Buchungsstatus.PENDING) {
                            sb[i][j].getSitz().setStatus(Buchungsstatus.FREI);
                            sb[i][j].buttonStatus();
                        }
                }
            }
        });

        fenster.add(top, BorderLayout.NORTH);
        fenster.add(center, BorderLayout.CENTER);
        fenster.add(bot, BorderLayout.SOUTH);
        fenster.setVisible(true);
    }

    private void logBooking(SitzButton sb){
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(new File(txtfile), true));
            pw.println("Gebucht: " + sb.getSitz() + "(" + Calendar.getInstance().getTime().toString() + ")");
            pw.close();
        } catch (IOException ex) {
            System.err.println("Fehler beim Schreiben in " + txtfile + ": " + ex.getLocalizedMessage());
        }
    }

    private class UhrzeitThread implements Runnable {
        private Thread thread;
        public void start() {
            if (thread == null) {
                thread = new Thread(this);
                thread.start();
            }
        }
        @Override
        public void run() {
            while (thread != null) {
                timer.setText(Calendar.getInstance().getTime().toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
