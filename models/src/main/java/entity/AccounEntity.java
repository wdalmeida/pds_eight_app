package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
public class AccounEntity {

    @Id
    private String iban;

    @Column(name="balance")
    private double balance;

    @JoinColumn(name="idtype")
    @ManyToOne
    private AccountTypeEntity accountTypeEntity;

    @JoinColumn(name="idclient")
    @ManyToOne
    private ClientEntity clientEntity;

    @Override
    public String toString() {
        return "AccounEntity{" +
                "iban='" + iban + '\'' +
                ", balance=" + balance +
                ", accountTypeEntity=" + accountTypeEntity +
                ", clientEntity=" + clientEntity +
                '}';
    }
}

