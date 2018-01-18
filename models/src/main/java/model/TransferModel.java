package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class TransferModel {

    private String sendingIBAN;

    private double amount;

    private String beneficiaryIban;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
