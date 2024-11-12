package org.example.gudilin.callservicepay.Model;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

/**
 * Класс модель записи для Spring Data JDBC*/
public class AccountCRUD {

    @Id
    private Integer id;

    private String name;
    private BigDecimal amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
