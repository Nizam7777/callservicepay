package org.example.gudilin.callservicepay.controllers;

import org.example.gudilin.callservicepay.Model.Account;
import org.example.gudilin.callservicepay.Model.TransferRequest;
import org.example.gudilin.callservicepay.localServices.TransferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    private final TransferService transferService;

    /**
     * Внедряем бин TransferService с помощью инъекции через конструктор из контекста Spring*/
    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public void transferAmount(
            @RequestBody TransferRequest request
    ){
        transferService.transferMoney(request.getSenderAccId(),
                request.getReceiverAccId(),
                request.getAmount());
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts(){
        return transferService.getAllAccounts();
    }
}
