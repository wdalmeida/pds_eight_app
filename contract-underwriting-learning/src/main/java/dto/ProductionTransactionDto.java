package dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@Getter
@Setter
public class ProductionTransactionDto {

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

    @Override
    public String toString() {
        return "ProductionTransactionDto{" +
                "date=" + date +
                ", id_client=" + id_client +
                ", id_account=" + id_account +
                ", client_last_name='" + client_last_name + '\'' +
                ", client_first_name='" + client_first_name + '\'' +
                ", client_birthday=" + client_birthday +
                ", client_adress='" + client_adress + '\'' +
                ", account_type='" + account_type + '\'' +
                ", account_product='" + account_product + '\'' +
                ", account_balance_before_transaction=" + account_balance_before_transaction +
                ", account_balance_after_transaction=" + account_balance_after_transaction +
                ", transaction_type='" + transaction_type + '\'' +
                ", transaction_amount=" + transaction_amount +
                ", transaction_sign='" + transaction_sign + '\'' +
                ", transaction_label='" + transaction_label + '\'' +
                ", id_account_beneficiary=" + id_account_beneficiary +
                '}';
    }
}
