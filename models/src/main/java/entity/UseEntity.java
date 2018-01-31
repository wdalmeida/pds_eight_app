package entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "connection")
public class UseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idcustomer;

    @Column(name="login")
    private String login;

    @Column(name = "password")
    private String password;
}
