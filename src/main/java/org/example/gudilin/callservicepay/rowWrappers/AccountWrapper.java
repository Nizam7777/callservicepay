package org.example.gudilin.callservicepay.rowWrappers;

import org.example.gudilin.callservicepay.Model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountWrapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rslt, int i) throws SQLException {
        Account acc = new Account();
        
        acc.setAmount(rslt.getBigDecimal("amount"));
        acc.setName(rslt.getString("name"));
        acc.setId(rslt.getInt("id"));

        return acc;
    }
}
