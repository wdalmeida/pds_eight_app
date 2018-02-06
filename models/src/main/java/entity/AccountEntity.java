package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "accoun")
public class AccountEntity {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    String accountNumber;


    @Column (name = "type")
    private String type;

    @Column (name = "balance")
    private int balance;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "account")
    private List<TransactionEntity> transactions;

    public AccountEntity(){};

    public AccountEntity(String accountNumber, String type, int balance) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = balance;
    }
}
