package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TransferModel {

    private String sendingIBAN;

    private double amount;

    private String beneficiaryIban;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate valueDate;

    private String wording;

    public String getSendingIBAN() {
        return this.sendingIBAN;
    }

    public void setSendingIBAN(String sendingIBAN) {
        this.sendingIBAN = sendingIBAN;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBeneficiaryIban() {
        return this.beneficiaryIban;
    }

    public void setBeneficiaryIban(String beneficiaryIban) {
        this.beneficiaryIban = beneficiaryIban;
    }

    public LocalDate getValueDate() {
        return this.valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public String getWording() {
        return this.wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

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
