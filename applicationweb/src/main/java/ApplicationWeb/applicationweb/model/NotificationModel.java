package ApplicationWeb.applicationweb.model;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="notification")
public class NotificationModel implements Serializable {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "notification_id_notification_seq", allocationSize=0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name="idnotification")
    private
    Integer idnotification;

    @Column(name="idaccount")
    private Integer idaccount;

    @Column(name="label")
    private String label;

    @Column(name="details")
    private String details;

    @Column(name="date")
    private String date;

    @Column(name="recipient")
    private String recipient;

    @Column(name="amount")
    private Float amount;

    @Column(name="status")
    private Boolean status;


    public NotificationModel() {
    }

    public NotificationModel(Integer idaccount, String label, String details, String date, String recipient, Float amount, Boolean status) {
        this.idaccount = idaccount;
        this.label = label;
        this.details = details;
        this.date = date;
        this.recipient = recipient;
        this.amount = amount;
        this.status = status;
    }


    public Integer getIdnotification() {
        return this.idnotification;
    }

    public void setIdnotification(Integer idnotification) {
        this.idnotification = idnotification;
    }

    public Integer getIdaccount() {
        return this.idaccount;
    }

    public void setIdaccount(Integer idaccount) {
        this.idaccount = idaccount;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
