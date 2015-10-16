package BookingSystem;

/**
 * Created by mincekara on 01.10.2015.
 */
public class Destination {
    private String name;
    private String ortsname;
    private String code;

    public Destination(String name, String ortsname, String code){
        this.name=name;
        this.ortsname=ortsname;
        this.code=code;
    }

    public String getName() {
        return name;
    }

    public String getOrtsname() {
        return ortsname;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrtsname(String ortsname) {
        this.ortsname = ortsname;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
