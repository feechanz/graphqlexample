package com.tryme.projectk.repository;

import com.tryme.projectk.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByUsername(String username);
}
