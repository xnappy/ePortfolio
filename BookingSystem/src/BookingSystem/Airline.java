package BookingSystem;

/**
 * Created by mincekara on 01.10.2015.
 */
public class Airline {
    private String code;
    private String name;

    Airline(String code, String name){
        super();
        this.code=code;
        this.name=name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
