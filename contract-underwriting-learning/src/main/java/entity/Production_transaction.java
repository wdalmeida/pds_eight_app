package entity;

import java.sql.Date;
import lombok.*;
import java.sql.Date;
import java.sql.Timestamp;

public class Production_transaction {

    private String id;
    private Timestamp date;
    private long id_client;
    private long id_account;
    private String client_last_name;
    private String client_first_name;
    private Date client_birthday;
    private String client_adress;
    private String account_type;
    private String account_product;
    private float account_balance_before_transaction;
    private float account_balance_after_transaction;
    private String transaction_type;
    private float transaction_amount;
    private String transaction_sign;
    private String transaction_label;
    private long id_account_beneficiary;

    public Production_transaction(String id, Timestamp date, long id_client, long id_account, String client_last_name, String client_first_name, Date client_birthday, String client_adress, String account_type, String account_product, float account_balance_before_transaction, float account_balance_after_transaction, String transaction_type, float transaction_amount, String transaction_sign, String transaction_label, long id_account_beneficiary) {
        this.id = id;
        this.date = date;
        this.id_client = id_client;
        this.id_account = id_account;
        this.client_last_name = client_last_name;
        this.client_first_name = client_first_name;
        this.client_birthday = client_birthday;
        this.client_adress = client_adress;
        this.account_type = account_type;
        this.account_product = account_product;
        this.account_balance_before_transaction = account_balance_before_transaction;
        this.account_balance_after_transaction = account_balance_after_transaction;
        this.transaction_type = transaction_type;
        this.transaction_amount = transaction_amount;
        this.transaction_sign = transaction_sign;
        this.transaction_label = transaction_label;
        this.id_account_beneficiary = id_account_beneficiary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public long getId_client() {
        return id_client;
    }

    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

    public long getId_account() {
        return id_account;
    }

    public void setId_account(long id_account) {
        this.id_account = id_account;
    }

    public String getClient_last_name() {
        return client_last_name;
    }

    public void setClient_last_name(String client_last_name) {
        this.client_last_name = client_last_name;
    }

    public String getClient_first_name() {
        return client_first_name;
    }

    public void setClient_first_name(String client_first_name) {
        this.client_first_name = client_first_name;
    }

    public Date getClient_birthday() {
        return client_birthday;
    }

    public void setClient_birthday(Date client_birthday) {
        this.client_birthday = client_birthday;
    }

    public String getClient_adress() {
        return client_adress;
    }

    public void setClient_adress(String client_adress) {
        this.client_adress = client_adress;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getAccount_product() {
        return account_product;
    }

    public void setAccount_product(String account_product) {
        this.account_product = account_product;
    }

    public float getAccount_balance_before_transaction() {
        return account_balance_before_transaction;
    }

    public void setAccount_balance_before_transaction(float account_balance_before_transaction) {
        this.account_balance_before_transaction = account_balance_before_transaction;
    }

    public float getAccount_balance_after_transaction() {
        return account_balance_after_transaction;
    }

    public void setAccount_balance_after_transaction(float account_balance_after_transaction) {
        this.account_balance_after_transaction = account_balance_after_transaction;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public float getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(float transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_sign() {
        return transaction_sign;
    }

    public void setTransaction_sign(String transaction_sign) {
        this.transaction_sign = transaction_sign;
    }

    public String getTransaction_label() {
        return transaction_label;
    }

    public void setTransaction_label(String transaction_label) {
        this.transaction_label = transaction_label;
    }

    public long getId_account_beneficiary() {
        return id_account_beneficiary;
    }

    public void setId_account_beneficiary(long id_account_beneficiary) {
        this.id_account_beneficiary = id_account_beneficiary;
    }
}
