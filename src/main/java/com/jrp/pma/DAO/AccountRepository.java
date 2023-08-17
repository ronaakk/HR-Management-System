package com.jrp.pma.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jrp.pma.entities.UserAccount;

public interface AccountRepository extends PagingAndSortingRepository<UserAccount, Long>{


}
