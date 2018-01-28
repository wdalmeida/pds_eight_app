package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="typeAccount")
public class AccountTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Label")
    private String label;

    @Column(name="Price")
    private double price;
}
