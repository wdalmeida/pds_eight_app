package fr.esipe.ing3.pds.eight.restaccountservices.services;

import fr.esipe.ing3.pds.eight.restaccountservices.models.AccountDto;

import java.util.List;

public interface IAccountService {

    List<AccountDto> getAllAccount();
}
