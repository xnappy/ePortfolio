package BookingSystem;

/**
 * Created by mincekara on 01.10.2015.
 */
public class BookingSystem {
    public static void main(String[] args) {
        Destination frankfurt = new Destination("Flughafen Berlin Brandenburg","Frankfurt am Main","FRA");
        Destination newyork = new Destination("International Airport 'John F. Kennedy'", "New York City", "NYC");
        Airline lufthansa = new Airline("DLH","Deutsche Lufthansa");
        Flug f = new Flug("LH4711", lufthansa, frankfurt, newyork, 15, 4);
        BookingForm bf = new BookingForm(f);
    }
}
