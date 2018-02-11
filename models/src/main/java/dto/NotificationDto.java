package dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class NotificationDto {

    private int IdNotification;

    private  String iban;

    private String label;

    private String detail;

    private Timestamp date;

    private String recipient;

    private String typeTransaction;

    private float amount;

    private boolean status;

}
