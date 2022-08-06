package com.heroku.springboot.heroku.app.repository;

import com.heroku.springboot.heroku.app.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
}
