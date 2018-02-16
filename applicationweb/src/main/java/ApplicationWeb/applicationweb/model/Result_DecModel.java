package ApplicationWeb.applicationweb.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="result_decouvert")
public class Result_DecModel {

    public Integer getIdresult() {
        return this.idresult;
    }

    public void setIdresult(Integer idresult) {
        this.idresult = idresult;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSocio() {
        return this.socio;
    }

    public void setSocio(String socio) {
        this.socio = socio;
    }

    public Float getBalance() {
        return this.balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @Id
    //@SequenceGenerator(name = "seq", sequenceName = "notification_id_notification_seq", allocationSize=0)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "idresult")
    private
    Integer idresult;

    @Column(name = "iban")
    private String iban;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "socio")
    private String socio;

    @Column(name = "balance")
    private Float balance;

    public Result_DecModel(Integer idresult, String iban, String name, String lastname, String socio, Float balance){
        this.idresult=idresult;
        this.iban=iban;
        this.name=name;
        this.lastname=lastname;
        this.socio=socio;
        this.balance=balance;
    }

    public Result_DecModel() {
    }
}