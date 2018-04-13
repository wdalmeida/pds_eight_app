package entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "production_transaction")
public class ProductionTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name="date")
    private Date date;
    @Column(name="id_client")
    private long id_client;
    @Column(name="id_account")
    private long id_account;
    @Column(name="client_last_name")
    private String client_last_name;
    @Column(name="client_first_name")
    private String client_first_name;
    @Column(name="client_birthday")
    private Date client_birthday;
    @Column(name="client_adress")
    private String client_adress;
    @Column(name="account_type")
    private String account_type;
    @Column(name="account_product")
    private String account_product;
    @Column(name="account_balance_before_transaction")
    private float account_balance_before_transaction;
    @Column(name="account_balance_after_transaction")
    private float account_balance_after_transaction;
    @Column(name="transaction_type")
    private String transaction_type;
    @Column(name="transaction_amount")
    private float transaction_amount;
    @Column(name="transaction_sign")
    private String transaction_sign;
    @Column(name="transaction_label")
    private String transaction_label;
    @Column(name="id_account_beneficiary")
    private long id_account_beneficiary;
}
