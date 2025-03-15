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
    public void addAccount(Account account) {
        accountRepository.save(account);
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
    public boolean updateAccountById(Long id, Account updatedAccount) {
        Optional<Account> accountOpt = accountRepository.findById(id);
        if(accountOpt.isPresent()) {
            Account toUpdate = accountOpt.get();
            toUpdate.setAccountNumber(updatedAccount.getAccountNumber());
            toUpdate.setBalance(updatedAccount.getBalance());
            toUpdate.setStatus(updatedAccount.getStatus());
            accountRepository.save(toUpdate);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAccountById(Long id) {
        Optional<Account>  accountOpt = accountRepository.findById(id);
        if(accountOpt.isPresent()){
            accountRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
