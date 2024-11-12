package org.example.gudilin.callservicepay.Model;

import java.math.BigDecimal;

/**
 * Класс DTO - класс моделирует данные передачи между сервисами\приложениями*/
public class TransferRequest {
    private long senderAccId;
    private long receiverAccId;
    private BigDecimal amount;

    public long getSenderAccId() {
        return senderAccId;
    }

    public void setSenderAccId(long senderAccId) {
        this.senderAccId = senderAccId;
    }

    public long getReceiverAccId() {
        return receiverAccId;
    }

    public void setReceiverAccId(long receiverAccId) {
        this.receiverAccId = receiverAccId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
