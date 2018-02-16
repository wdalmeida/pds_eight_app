package entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;


@Entity
@Table(name="notification")
public class NotificationEntity {
    public Integer getIdNotification() {
        return IdNotification;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdNotification;

    @Column(name="iban")
    private  String iban;

    @Column(name="detail")
    private String detail;

    @Column(name="label")
    private String label;

    @Column(name="date")
    private Timestamp date;

    @Column(name="recipient")
    private String recipient;

    public void setIdNotification(Integer idNotification) {
        IdNotification = idNotification;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Column(name="typeTransaction")
    private String typeTransaction;

    @Column(name="amount")
    private Double amount;

    @Column(name="status")
    private Boolean status;

}
