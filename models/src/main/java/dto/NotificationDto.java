package dto;



import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationDto {

    private Integer IdNotification;

    private String iban;

    private String label;

    private String detail;

    private Date date;

    private String recipient;

    private String typeTransaction;

    private Double amount;

    private Boolean status;


}
