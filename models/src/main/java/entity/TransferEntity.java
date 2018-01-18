package entity;

import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Entity(name = "transfer")
public class TransferEntity {

    private long id;

    private String sendingIBAN;

    private double amount;

    private String beneficiaryIban;

    private LocalDate valueDate;

    private String wording;

    @Override
    public String toString() {
        return "TransferModel{" +
                "sendingIBAN='" + sendingIBAN + '\'' +
                ", amount=" + amount +
                ", beneficiaryIban='" + beneficiaryIban + '\'' +
                ", valueDate=" + valueDate +
                ", wording='" + wording + '\'' +
                '}';
    }
}
