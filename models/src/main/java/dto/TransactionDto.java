package dto;

import java.time.LocalDate;

public class TransactionDto {

    private int idtransaction;

    private LocalDate valueDate;

    private double amount;

    private String wording;

    private String description;

    private AccountDto account;

    private boolean read;

    public TransactionDto(int idtransaction) {
        this.idtransaction = idtransaction;
    }

    public TransactionDto(int idtransaction, LocalDate valueDate, double amount, String wording, String description, AccountDto account, boolean read) {
        this.idtransaction = idtransaction;
        this.valueDate = valueDate;
        this.amount = amount;
        this.wording = wording;
        this.description = description;
        this.account = account;
        this.read = read;
    }

    public int getIdtransaction() {
        return idtransaction;
    }

    public void setIdtransaction(int idtransaction) {
        this.idtransaction = idtransaction;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "idtransaction=" + idtransaction +
                ", valueDate=" + valueDate +
                ", amount=" + amount +
                ", wording='" + wording + '\'' +
                ", description='" + description + '\'' +
                ", account=" + account +
                ", read=" + read +
                '}';
    }
}
