package com.heroku.springboot.heroku.app.domain.dto;

import lombok.Data;

@Data
public class ExpenseDetailDTO {
    private Long totalIncome;
    private Long totalExpenses;
    private String monthAndYear;
}
