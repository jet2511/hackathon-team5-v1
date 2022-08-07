package com.heroku.springboot.heroku.app.domain.dto;

import lombok.Data;

@Data
public class FinanceTargetCreateRequestDto {
    private Long financeCategoryId;
    private Long amount;
    private String note;
    private Long incomeExtras;
    private Integer numOfMonths;
}
