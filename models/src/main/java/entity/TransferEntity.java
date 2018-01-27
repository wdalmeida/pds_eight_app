package entity;

import lombok.Data;
import repository.TransferRepository;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "transfer")
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "beneficiaryIban")
    private String beneficiaryIban;

    @OneToOne
    private TransactionEntity TransactionEntity;
}
