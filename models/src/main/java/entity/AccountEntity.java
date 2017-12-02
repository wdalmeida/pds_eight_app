package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long accountNumber;

    @Column (name = "type")
    private String type;

    @Column (name = "balance")
    private int balance;

    @ManyToOne
    private UserEntity user;

}
