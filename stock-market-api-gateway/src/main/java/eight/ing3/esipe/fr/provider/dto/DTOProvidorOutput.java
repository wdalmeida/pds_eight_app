package eight.ing3.esipe.fr.provider.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by Vyach on 12/02/2018.
 */
public class DTOProvidorOutput {

    private final Logger logger = LoggerFactory.getLogger(DTOProvidorOutput.class);

    private String id;
    private Date date;
    private String hour;
    private double value;
    private String srcCurrency;
    private String targetCurrency;


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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getSrcCurrency() {
        return srcCurrency;
    }

    public void setSrcCurrency(String srcCurrency) {
        this.srcCurrency = srcCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public DTOProvidorOutput() {

    }

    //convert DTOProvidorA inot DTOProvidorOutput
    public DTOProvidorOutput(DTOProvidorA dtoProvidorA) {

        logger.info("DTO Providor A ID : " + dtoProvidorA.getId());

        this.id = dtoProvidorA.getId();
        this.date = dtoProvidorA.getDate();
        this.hour = dtoProvidorA.getDateHour();
        this.srcCurrency = dtoProvidorA.getFromCurrency();
        this.targetCurrency = dtoProvidorA.getToCurrency();
        this.value = dtoProvidorA.getValue();

    }

    //convert DTOProvidorB inot DTOProvidorOutput
    public DTOProvidorOutput(DTOProvidorB dtoProvidorB) {

        logger.info("DTO Providor B ID : " + dtoProvidorB.getId());

        this.id = dtoProvidorB.getId();
        this.date = dtoProvidorB.getDate();
        this.hour = dtoProvidorB.getDateHour();
        this.srcCurrency = "USD";
        this.targetCurrency = "EUR";
        this.value = dtoProvidorB.getValue();

    }
}
