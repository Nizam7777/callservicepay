package org.example.gudilin.callservicepay.localServices;

import org.example.gudilin.callservicepay.Model.AccountCRUD;
import org.example.gudilin.callservicepay.Model.logging;
import org.example.gudilin.callservicepay.Repositories.AccountCRUDRepository;
import org.example.gudilin.callservicepay.exeptions.AccountNotFindExeption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransferCRUDService {
    private final AccountCRUDRepository accountCRUDRepository;
    private final logging logging;

    /**
     * Внедряем бин accountCRUDRepository из контекста Spring-a
     * */
    public TransferCRUDService(AccountCRUDRepository accountCRUDRepository, logging logging) {
        this.accountCRUDRepository = accountCRUDRepository;
        this.logging = logging;
    }

    @Transactional
    /**
     * вызов этого метода выполняется в транзакции с попмощью Аннотации @Transactionl
     * */
    public void transferMoney(long IdSender, long IdReceiver, BigDecimal amount){
        try {
            AccountCRUD sender = accountCRUDRepository.findByIdAccountCRUD(IdSender).orElseThrow(() -> new AccountNotFindExeption());    // Получаем сведения о счете отправителе
            AccountCRUD receiver = accountCRUDRepository.findByIdAccountCRUD(IdReceiver).orElseThrow(() -> new AccountNotFindExeption());  // Получаем сведения о счете получателе

            BigDecimal senderAmount = sender.getAmount().subtract(amount);   // Вычисляем новый баланс счета отправителя
            BigDecimal receiverAmount = receiver.getAmount().add(amount);      // Вычисляем новый баланс счета получателя

            accountCRUDRepository.changeAmount(IdSender, senderAmount);            // Присваиваем новый баланс для счета отправителя
            accountCRUDRepository.changeAmount(IdReceiver, receiverAmount);        // Присваиваем новый баланс для счета отправителя
        }
        catch(RuntimeException ex){
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Метод возвращает все счета, которые находятся в БД
     */
    public Iterable<AccountCRUD> getAllAccounts(){
        return accountCRUDRepository.findAll();
    }

    public Iterable<AccountCRUD> getAllAccountsCRUD(){
        return accountCRUDRepository.getAllAccountsCRUD();
    }

    public List<AccountCRUD> mmfindAccountsByName(String name222){
        logging.getInfo("mmfindAccountsByName_22222 = " + name222);

        /*
        * супер коммент*/
        return accountCRUDRepository.myFindAccountCRUDByName(name222);
    }
}
