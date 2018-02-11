package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "beneficiaryaccount")
public class BeneficiaryAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idbeneficiary;

    @JoinColumn(name = "idclient")
    @ManyToOne
    private ClientEntity clientEntity;

    @Column(name = "iban")
    private String iban;

    @Column(name = "name")
    private String name;
}
