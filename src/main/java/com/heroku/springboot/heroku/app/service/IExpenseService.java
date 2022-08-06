package com.heroku.springboot.heroku.app.service;

import com.heroku.springboot.heroku.app.entity.ExpenseEntity;

import java.util.List;

public interface IExpenseService {
    List<ExpenseEntity> getAll();

    ExpenseEntity save(ExpenseEntity expenseEntity);
}
