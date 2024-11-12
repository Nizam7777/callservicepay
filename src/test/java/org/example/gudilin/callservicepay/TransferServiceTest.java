package org.example.gudilin.callservicepay;

import org.example.gudilin.callservicepay.Model.Account;
import org.example.gudilin.callservicepay.Repositories.AccountRepository;
import org.example.gudilin.callservicepay.exeptions.AccountNotFindExeption;
import org.example.gudilin.callservicepay.localServices.TransferService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class TransferServiceTest {
    @Test
    public void transferServiceHappy() {
        AccountRepository accountRepository =
                mock(AccountRepository.class);

        TransferService transferService =
                new TransferService(accountRepository);

        Account sender = new Account();
        sender.setName("dddd");
        sender.setId(1l);
        sender.setAmount(new BigDecimal(1200));

        Account receiver = new Account();
        receiver.setName("aaaaa");
        receiver.setId(2l);
        receiver.setAmount(new BigDecimal(5000));

        given(accountRepository.findAccountById(sender.getId())).willReturn(sender);
        //given(accountRepository.findAccountById(receiver.getId())).willReturn(null);
        given(accountRepository.findAccountById(receiver.getId())).willReturn(receiver);

        transferService.transferMoney(sender.getId(),receiver.getId(),new BigDecimal(500));

        verify(accountRepository)
                .changeAmount(sender.getId(),new BigDecimal(700));
        verify(accountRepository)
                .changeAmount(receiver.getId(), new BigDecimal(5500));

        /*assertThrows(AccountNotFindExeption.class,
                ()->transferService.transferMoney(1,2,new BigDecimal(200)));
        verify(accountRepository,never())
                .changeAmount(anyLong(), any());*/
    }
}
