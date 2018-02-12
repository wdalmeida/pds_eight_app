package entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;


@Entity
@Table(name="notification")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationEntity {

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

    @Column(name="typeTranaction")
    private String typeTranaction;

    @Column(name="amount")
    private Double amount;

    @Column(name="status")
    private Boolean status;
}
