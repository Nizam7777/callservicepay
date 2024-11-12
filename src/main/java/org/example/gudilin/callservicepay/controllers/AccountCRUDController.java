package org.example.gudilin.callservicepay.controllers;

import org.example.gudilin.callservicepay.Model.AccountCRUD;
import org.example.gudilin.callservicepay.Model.TransferRequest;
import org.example.gudilin.callservicepay.Model.logging;
import org.example.gudilin.callservicepay.localServices.TransferCRUDService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountCRUDController {
    private final TransferCRUDService transferCRUDService;
    private final logging logging;
    /**
     * Внедряем бин TransferCRUDService с помощью инъекции через конструктор из контекста Spring*/
    public AccountCRUDController(TransferCRUDService transferCRUDService, logging logging) {
        this.transferCRUDService = transferCRUDService;
        this.logging = logging;
    }

    @PostMapping("/transferCRUD")
    public void transferAmount(
            @RequestBody TransferRequest request
    ){
        transferCRUDService.transferMoney(request.getSenderAccId(),
                request.getReceiverAccId(),
                request.getAmount());
    }

    @GetMapping("/accountsCRUD")
    public List<AccountCRUD> getAllAccounts(
            @RequestBody(required = false) String name222
    ){
        logging.getInfo("name222 = " + name222);

        if(name222 == null){
            return (List<AccountCRUD>) transferCRUDService.getAllAccountsCRUD();
        }
        else{
            return transferCRUDService.mmfindAccountsByName(name222);
        }
    }
}
