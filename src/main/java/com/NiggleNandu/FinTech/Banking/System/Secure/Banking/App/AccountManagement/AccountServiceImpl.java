package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.AccountManagement;

import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.FundTransfer.ExternalTransferRequest;
import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.FundTransfer.ExternalTransferResponse;
import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.FundTransfer.FundTransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
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
    public Optional<Account> getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
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

    @Override
    public Optional<String> transferFunds(FundTransferRequest request) {
        return accountRepository.findByAccountNumber(request.getFromAccountNumber())
                .filter(sender -> request.getAmount().compareTo(BigDecimal.ZERO) > 0)
                .filter(sender -> sender.getBalance().compareTo(request.getAmount()) >= 0)
                .flatMap(sender -> {
                    sender.setBalance(sender.getBalance().subtract(request.getAmount()));
                    accountRepository.save(sender);

                    if (!request.isExternalTransfer()) {

                        return Optional.of(processInternalTransfer(request));
                    } else {
                        return processExternalTransfer(request);
                    }
                });
    }

    private String processInternalTransfer(FundTransferRequest request){
        return accountRepository.findByAccountNumber(request.getToAccountNumber())
                .map(receiver -> {
                    receiver.setBalance(receiver.getBalance().add(request.getAmount()));
                    accountRepository.save(receiver);
                    return "Transfer successful: Internal";
                }).orElse("failed: Receiver account not found");
    }

    private Optional<String> processExternalTransfer(FundTransferRequest request){
        String externalAPiUrl ="http://localhost:8080/api/funds/transfer";
        try {
            ExternalTransferRequest externalRequest = new ExternalTransferRequest(
                    request.getAmount(),
                    Long.parseLong(request.getFromAccountNumber()),
                    Long.parseLong(request.getToAccountNumber())
            );

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<ExternalTransferRequest> httpEntity = new HttpEntity<>(externalRequest);
            //ResponseEntity<ExternalTransferResponse> response = restTemplate.postForEntity(externalAPiUrl, httpEntity, ExternalTransferResponse.class);
            ResponseEntity<String> response = restTemplate.postForEntity(externalAPiUrl, httpEntity, String.class);

            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null && response.getBody().toLowerCase().contains("success")){
                return Optional.of("Transfer successful: External");
            } else {
                return Optional.of("Failed: External transfer error");
            }
        } catch (Exception e){
            e.printStackTrace();
            return Optional.of("Failed: Unable to process external transfer");
        }
    }
}