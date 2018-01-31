package entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="typeaccount")
public class AccountTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtype;

    @Column(name="label")
    private String label;

    @Column(name="price")
    private double price;
}