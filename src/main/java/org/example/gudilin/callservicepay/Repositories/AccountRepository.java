package org.example.gudilin.callservicepay.Repositories;

import org.example.gudilin.callservicepay.Model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.example.gudilin.callservicepay.rowWrappers.AccountWrapper;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {
    private final JdbcTemplate jdbc;

    public AccountRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Account findAccountById(long id ){
        String sql = "SELECT * FROM account WHERE id = ?";
        return jdbc.queryForObject(sql, new AccountWrapper(), id);
    }

    public void changeAmount( long id, BigDecimal amount ){
        String sql = "UPDATE account SET amount = ? WHERE id = ?";
        jdbc.update(sql, amount, id );
    }

    public List<Account> findAllAccount(){
        String sql = "SELECT * FROM account";
        return jdbc.query(sql, new AccountWrapper());
    }
}
