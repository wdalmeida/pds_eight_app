package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Account")
public class AccounEntity {

    @Id
    private String iban;

    @Column(name="Balance")
    private double balance;

    @ManyToOne
    private AccountTypeEntity accountTypeEntity;

    @ManyToOne
    private ClientEntity clientEntity;


}
