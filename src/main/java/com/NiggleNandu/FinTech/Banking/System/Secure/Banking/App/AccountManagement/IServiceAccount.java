package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.AccountManagement;

import java.util.List;
import java.util.Optional;

public interface IServiceAccount {
    void addAccount(Account account);
    List<Account> getAllAccount();
    Optional<Account> getAccountById(Long id);
    boolean updateAccountById(Long id, Account account);
    boolean deleteAccountById(Long id);
}
