package com.heroku.springboot.heroku.app.repository;

import com.heroku.springboot.heroku.app.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
