package org.example.gudilin.callservicepay.localServices;

import org.example.gudilin.callservicepay.Model.Account;
import org.example.gudilin.callservicepay.Repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {
    private final AccountRepository accountRepository;

    /**
     * Внедряем бин AccountRepository из контекста Spring-a
     * */
    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    /**
     * вызов этого метода выполняется в транзакции с попмощью Аннотации @Transactionl
     * */
    public void transferMoney(long IdSender, long IdReceiver, BigDecimal amount){
        Account sender   = accountRepository.findAccountById(IdSender);    // Получаем сведения о счете отправителе
        Account receiver = accountRepository.findAccountById(IdReceiver);  // Получаем сведения о счете получателе

        BigDecimal senderAmount   = sender.getAmount().subtract(amount);   // Вычисляем новый баланс счета отправителя
        BigDecimal receiverAmount = receiver.getAmount().add(amount);      // Вычисляем новый баланс счета получателя

        accountRepository.changeAmount(IdSender, senderAmount);            // Присваиваем новый баланс для счета отправителя
        accountRepository.changeAmount(IdReceiver, receiverAmount);        // Присваиваем новый баланс для счета отправителя

        //throw new RuntimeException("Oh no! Something went wrong!");
    }

    /**
     * Метод возвращает все счета, которые находятся в БД
     * */
    public List<Account> getAllAccounts(){
        return accountRepository.findAllAccount();
    }
}
