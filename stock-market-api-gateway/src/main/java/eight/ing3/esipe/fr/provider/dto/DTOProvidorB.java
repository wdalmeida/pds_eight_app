package eight.ing3.esipe.fr.provider.dto;

import java.util.Date;

/**
 * Created by Vyach on 12/02/2018.
 */
public class DTOProvidorB {

    private String id;
    private Date date;
    private String dateHour;
    private double value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateHour() {
        return dateHour;
    }

    public void setDateHour(String dateHour) {
        this.dateHour = dateHour;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
