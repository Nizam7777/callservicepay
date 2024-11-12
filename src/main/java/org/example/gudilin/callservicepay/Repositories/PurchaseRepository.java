package org.example.gudilin.callservicepay.Repositories;

import org.example.gudilin.callservicepay.Model.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseRepository {
    private final JdbcTemplate jdbcTemplate;

    public PurchaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void storePurchase(Purchase purchase){
        String sql =
                "INSERT INTO purchase VALUES(?,?,?)";

        jdbcTemplate.update(sql, purchase.getId(), purchase.getProduct(), purchase.getPrice());
    }

    public List<Purchase> findAllPurchaces(){
        String sql =
                "SELECT * from purchase";

        RowMapper<Purchase> purchaseRowMapper = (r, l) -> {
            Purchase rowObject = new Purchase();

            rowObject.setId(r.getInt("id"));
            rowObject.setProduct(r.getString("product"));
            rowObject.setPrice(r.getBigDecimal("price"));

            return rowObject;
        };

        return jdbcTemplate.query(sql, purchaseRowMapper);
    }
}
