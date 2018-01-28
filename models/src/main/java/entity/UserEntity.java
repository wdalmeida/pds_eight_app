package entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

/*@Data
@Entity(name = "user")
public class UserEntity {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    long userId;

    @Column (name = "password")
    String password;

    @Column (name = "first_name")
    String firstName;
    @Column (name = "last_name")
    String lastName;

    @OneToMany(mappedBy = "user")
    private List<AccountEntity> accounts;

    public UserEntity(){};

    public UserEntity(String password, String firstName, String lastName, List<AccountEntity> accounts) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = accounts;
    }
}*/
