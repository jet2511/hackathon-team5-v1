package com.heroku.springboot.heroku.app.repository;

import com.heroku.springboot.heroku.app.entity.FinanceTargetDetailEntity;
import com.heroku.springboot.heroku.app.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFinanceTargetDetailRepository extends JpaRepository<FinanceTargetDetailEntity, Long> {
}
