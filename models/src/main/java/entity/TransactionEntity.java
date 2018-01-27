package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="Transaction")
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
    private AccountEntity accountEntity;

    @Column(name="Read")
    private boolean read;
}
