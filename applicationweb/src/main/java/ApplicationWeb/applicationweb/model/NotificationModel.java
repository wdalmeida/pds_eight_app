
package ApplicationWeb.applicationweb.model;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="notification")
public class NotificationModel{

    @Id
    //@SequenceGenerator(name = "seq", sequenceName = "notification_id_notification_seq", allocationSize=0)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name="idnotification")
    private
    Integer idnotification;

    @Column(name="iban")
    private String iban;

    @Column(name="label")
    private String label;

    @Column(name="detail")
    private String detail;

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

    public NotificationModel(String iban, String label, String detail, String date, String recipient, Float amount, Boolean status) {
        this.iban = iban;
        this.label = label;
        this.detail = detail;
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

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

@Override
    public String toString(){
        return "Notification{"+
                "id="+ idnotification+
                ", details='"+ amount+ '\''+
                ", recipient='"+recipient+'\''+
                ", label='"+label+'\''+
                ", amount"+amount+
                '}';
}

}

