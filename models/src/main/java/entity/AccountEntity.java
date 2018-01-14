package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "account")
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

    public AccountEntity(){};

    public AccountEntity(String accountNumber, String type, int balance) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = balance;
    }
}
