package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDto {

    private CredentialDto credential;
    private String firstName;
    private String lastName;
    private List<AccountDto> accountDtoList;





}
