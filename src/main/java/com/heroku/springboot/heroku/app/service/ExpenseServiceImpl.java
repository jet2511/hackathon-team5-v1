package com.heroku.springboot.heroku.app.service;

import com.heroku.springboot.heroku.app.entity.ExpenseEntity;
import com.heroku.springboot.heroku.app.repository.IExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements IExpenseService{
    @Autowired
    private IExpenseRepository expenseRepository;

    @Override
    public List<ExpenseEntity> getAll() {
        return expenseRepository.findAll();
    }

    @Override
    public ExpenseEntity save(ExpenseEntity expenseEntity) {
        return expenseRepository.save(expenseEntity);
    }
}
