package entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long userId;

    @Column (name = "password")
    String password;

    @Column (name = "first_name")
    String firstName;
    @Column (name = "last_name")
    String lastName;

    @OneToMany(mappedBy = "user")
    private List<AccountEntity> accounts;




}
