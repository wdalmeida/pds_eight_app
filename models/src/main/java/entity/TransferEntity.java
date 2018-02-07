package entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "transfer")
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idtransfer;

    @Column(name = "beneficiaryiban")
    private String beneficiaryIban;

    @JoinColumn(name = "idtransaction")
    @OneToOne
    private TransactionEntity transactionEntity;

}
