package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idclient;

    @JoinColumn(name = "idcustomer")
    @OneToOne
    private UseEntity userEntity;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String LastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "socio")
    private String socio;

    @Column(name = "salary")
    private double salary;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mail")
    private String mail;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "birthplacecity")
    private String birthplaceCity;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "situation")
    private String situation;

    @Column(name = "dateinscription")
    private String dateInscription;

    @Column(name = "child")
    private int child;

    @Column(name="heritage")
    private boolean heritage;

    @Column(name="permanentjob")
    private boolean permanentJob;

}
