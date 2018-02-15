package dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class NotificationDto {

    private
    int idnotification;

    private Integer idaccount;

    private String label;

    private String details;

    private String date;

    private String recipient;

    private Float amount;

    private Boolean status;
}
