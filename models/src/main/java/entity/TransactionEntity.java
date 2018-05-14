package entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtransaction;

    @Column(name="valuedate")
    private LocalDate valueDate;

    @Column(name="amount")
    private double amount;

    @Column(name="wording")
    private String wording;

    @Column(name="description")
    private String description;

    @JoinColumn(name="iban")
    @ManyToOne
    private AccounEntity accountEntity;

    @Column(name="read")
    private boolean read;
}
