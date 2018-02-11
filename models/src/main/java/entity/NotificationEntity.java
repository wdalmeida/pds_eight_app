package entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@Table(name="notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdNotification;

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

    @Column(name="typeTranaction")
    private String typeTranaction;

    @Column(name="amount")
    private float amount;

    @Column(name="status")
    private boolean status;
}
