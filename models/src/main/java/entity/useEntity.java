package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Connection")
public class useEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;

    @Column(name="Login")
    private String login;

    @Column(name = "Password")
    private String password;
}
