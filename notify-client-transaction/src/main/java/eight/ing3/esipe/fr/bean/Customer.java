package eight.ing3.esipe.fr.bean;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@Table (name = "customer", schema = "sch")
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer idCustomer;
    @Column (name = "name")
    String name;
}
