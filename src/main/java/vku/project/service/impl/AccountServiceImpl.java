package vku.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vku.project.entity.Account;
import vku.project.repository.AccountRepository;
import vku.project.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void save(Account account) {
        this.accountRepository.save(account);
    }
}
