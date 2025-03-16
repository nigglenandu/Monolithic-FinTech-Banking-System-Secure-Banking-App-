package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.AccountManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IServiceAccount {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> updateAccountById(Long id, Account updatedAccount) {
        return accountRepository.findById(id).
                map(account -> {
                    account.setAccountNumber(updatedAccount.getAccountNumber());
                    account.setBalance(updatedAccount.getBalance());
                    account.setStatus(updatedAccount.getStatus());
                    return accountRepository.save(account);
                });
    }

    @Override
    public boolean deleteAccountById(Long id) {
        if(accountRepository.existsById(id)){
            accountRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
