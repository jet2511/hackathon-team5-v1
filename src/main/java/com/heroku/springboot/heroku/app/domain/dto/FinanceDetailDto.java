package com.heroku.springboot.heroku.app.domain.dto;

import lombok.Data;

@Data
public class FinanceDetailDto {
    private Long id;
    private String categoryName;
    private String completePercent;
    private String status;
    private Long amount;
}
