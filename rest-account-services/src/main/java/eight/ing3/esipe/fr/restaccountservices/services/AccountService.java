package eight.ing3.esipe.fr.restaccountservices.services;

import eight.ing3.esipe.fr.restaccountservices.models.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    private final AccountDto account;

    @Autowired
    public AccountService(AccountDto account) {
        this.account = account;
    }

    @Override
    public List<AccountDto> getAllAccount() {
        return account.getAccountList() ;
    }
}
