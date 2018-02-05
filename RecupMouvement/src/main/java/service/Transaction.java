package service;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;


public final class Transaction {
    @JacksonXmlProperty(localName = "type", isAttribute = true)
    private String type;
    @JacksonXmlProperty(localName = "personne")
    private String personne;
    @JacksonXmlProperty(localName = "ibansender")
    private String ibansender;
    @JacksonXmlProperty(localName = "date")
    private Date date;
    @JacksonXmlProperty(localName = "intitule")
    private String intitule;
    @JacksonXmlProperty(localName = "montant")
    private float montant;
    @JacksonXmlProperty(localName = "detail")
    private String detail;
    @JacksonXmlProperty(localName = "ibanrecipient")
    private String ibanrecipient;

    public Transaction() {
    }

    public Transaction(String type, String personne, String ibansender, Date date, String intitule, float montant, String detail, String ibanrecipient) {
        this.type = type;
        this.personne = personne;
        this.ibansender = ibansender;
        this.date = date;
        this.intitule = intitule;
        this.montant = montant;
        this.detail = detail;
        this.ibanrecipient = ibanrecipient;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPersonne() {
        return personne;
    }

    public void setPersonne(String personne) {
        this.personne = personne;
    }

    public String getIbansender() {
        return ibansender;
    }

    public void setIbansender(String ibansender) {
        this.ibansender = ibansender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIbanrecipient() {
        return ibanrecipient;
    }

    public void setIbanrecipient(String ibanrecipient) {
        this.ibanrecipient = ibanrecipient;
    }
}



