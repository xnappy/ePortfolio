package BookingSystem;

import java.util.Calendar;

/**
 * Created by mincekara on 01.10.2015.
 */
public class Flug {
    private String name;
    private Airline airlane;
    private Destination from;
    private Destination to;
    private Calendar departure;
    private Calendar landing;
    private int rows;
    private int seats;

    private Sitz[][] sitze;

    public Flug(String name, Airline airlane, Destination from, Destination to, int rows, int seats){
        this.name=name;
        this.airlane=airlane;
        this.from=from;
        this.to=to;
        this.rows=rows;
        this.seats=seats;

        sitze = new Sitz[rows][seats];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < seats; j++){
                //Alternative zum casten:
                //char reihe = 'A'
                //sitze[i][j] = new Sitz(i+1, reihe++);
                sitze[i][j] = new Sitz(i+1,(char)('A' + j));
            }
        }
    }

    public int freiePlaetze(){
        int counter = 0;

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < seats; j++){
                if (sitze[i][j].getStatus()==Buchungsstatus.FREI){
                    counter++;
                }
            }
        }

        return counter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAirlane(Airline airlane) {
        this.airlane = airlane;
    }

    public void setFrom(Destination from) {
        this.from = from;
    }

    public void setTo(Destination to) {
        this.to = to;
    }

    public void setDeparture(Calendar departure) {
        this.departure = departure;
    }

    public void setLanding(Calendar landing) {
        this.landing = landing;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public Airline getAirlane() {
        return airlane;
    }

    public Destination getFrom() {
        return from;
    }

    public Destination getTo() {
        return to;
    }

    public Calendar getDeparture() {
        return departure;
    }

    public Calendar getLanding() {
        return landing;
    }

    public int getRows() {
        return rows;
    }

    public int getSeats() {
        return seats;
    }

    public Sitz[][] getSitze() {
        return sitze;
    }
}
