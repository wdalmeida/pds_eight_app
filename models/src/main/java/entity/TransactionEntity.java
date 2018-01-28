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
    private int id;

    @Column(name="ValueDate")
    private LocalDate valueDate;

    @Column(name="Amount")
    private double amount;

    @Column(name="Wording")
    private String wording;

    @ManyToOne
    private AccounEntity accountEntity;

    @Column(name="Read")
    private boolean read;
}
