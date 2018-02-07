package eight.ing3.esipe.fr.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification {

        @Id
        @SequenceGenerator(name="notification_id_notification_seq",
                          sequenceName="notification_id_notification_seq",
                          allocationSize=1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE,
                        generator = "notification_id_notification_seq")
        @Column(name = "id_notification")
        int idNotification;
        @Column (name = "idaccount")
        Integer idAccount;
        @Column (name = "label")
        String label;
        @Column (name = "details")
        String details;
        @Column (name = "date")
        Date date;
        @Column (name = "recipient")
        String recipient;
        @Column (name = "typetransac")
        String typeTransac;
        @Column (name = "amount")
        Double amount;

        public Notification(){

        }

    public Notification(Integer idAccount, String label, String details, Date date, String recipient, String typeTransac, Double amount, String status) {
        this.idAccount = idAccount;
        this.label = label;
        this.details = details;
        this.date = date;
        this.recipient = recipient;
        this.typeTransac = typeTransac;
        this.amount = amount;
        this.status = status;
    }

    @Column (name = "status")
        String status;



        public int getIdNotification() {
                return idNotification;
        }

        public void setIdNotification(int idNotification) {
                this.idNotification = idNotification;
        }

        public Integer getIdAccount() {
                return idAccount;
        }

        public void setIdAccount(Integer idAccount) {
                this.idAccount = idAccount;
        }

        public String getLabel() {
                return label;
        }

        public void setLabel(String label) {
                this.label = label;
        }

        public String getDetails() {
                return details;
        }

        public void setDetails(String details) {
                this.details = details;
        }


        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        public String getRecipient() {
                return recipient;
        }

        public void setRecipient(String recipient) {
                this.recipient = recipient;
        }

        public String getTypeTransac() {
                return typeTransac;
        }

        public void setTypeTransac(String typeTransac) {
                this.typeTransac = typeTransac;
        }

        public Double getAmount() {
                return amount;
        }

        public void setAmount(Double amount) {
                this.amount = amount;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }


}