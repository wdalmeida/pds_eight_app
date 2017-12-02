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

    private String type;

    private int balance;

    //private static List<AccountDto> accountList;

   /* static {
        accountList = new ArrayList<>();
        AccountDto a1 = new AccountDto(Long.parseLong("1"),"CCP",200);
        AccountDto a2 = new AccountDto(Long.parseLong("2"),"LivretA",400);
        accountList.add(a1);
        accountList.add(a2);
    }
*/

  //  public static List<AccountDto> getAccountList() {
        //return accountList;
    }

