package eight.ing3.esipe.fr.entities;


import lombok.*;

import java.sql.Date;
 
public class Transaction {

    private String personne;
    private String ibansender;
    private String intitule;
    private Double montant;
    private String detail;
    private String ibanrecipient;
    private String tpe;
    private Date date;

    public Transaction() {
    }

    public Transaction(String personne, String ibansender, String intitule, Double montant, String detail, String ibanrecipient, String tpe, Date date) {
        this.personne = personne;
        this.ibansender = ibansender;
        this.intitule = intitule;
        this.montant = montant;
        this.detail = detail;
        this.ibanrecipient = ibanrecipient;
        this.tpe = tpe;
        this.date = date;
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

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
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

    public String getTpe() {
        return tpe;
    }

    public void setTpe(String tpe) {
        this.tpe = tpe;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                ", ibansender='" + ibansender + '\'' +
                ", intitule='" + intitule + '\'' +
                ", montant='" + montant + '\'' +
                ", date=" + date +
                '}';
    }
}
