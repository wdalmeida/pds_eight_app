package dto;

import entity.AccounEntity;

import javax.persistence.*;
import java.time.LocalDate;

public class Transaction {

    private int idtransaction;

    private LocalDate valueDate;

    private double amount;

    private String wording;

    private AccounEntity accountEntity;

    private boolean read;
}
