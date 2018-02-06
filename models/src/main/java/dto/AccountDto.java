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
public class AccountDto {


    private String accountNumber;

    private AccountType type;

    private int balance;

    private List<TransactionDto> transactionDtoList;

    }

