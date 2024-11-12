package org.example.gudilin.callservicepay.Repositories;

import org.example.gudilin.callservicepay.Model.AccountCRUD;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountCRUDRepository extends CrudRepository<AccountCRUD, Long> {
    /**
     * Получение данных обо всех счетах с переданным именем
     * Соотв запросу select * from account where name = %name%
     *
     * Запрос расшифровывается при старте приложения, что замедляет запуск приложения*/
    List<AccountCRUD> findAccountsByName(String name);


    /*
     * Запрос вызывается при вызове метода, а не при старте приложения
     * имя метода не важен, вызвает тот запрос, что написан в аннотации @Query*/
    @Query("select * from account where name = :name222")
    List<AccountCRUD> myFindAccountCRUDByName(String name222);

    @Query("select * from account")
    List<AccountCRUD> getAllAccountsCRUD();

    @Query("select * from account WHERE id = :ident")
    Optional<AccountCRUD> findByIdAccountCRUD(long ident);

    /**
     * Аннотация @Modifying применяется к метолоам изменения записей в запросе @Query
     * INSERT, UPDATE, DELETE*/
    @Modifying
    @Query("UPDATE account SET amount = :amount WHERE id = :id")
    void changeAmount(long id, BigDecimal amount);
}
