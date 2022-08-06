package com.heroku.springboot.heroku.app.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class FiniconHomepageDTO {
    private Long totalAmount;
    private List<AssetDto> assetDtos;
    private List<ExpenseDetailDTO> expenseDetailDtos;
    private List<FinanceDetailDto> financeDetailDtos;
}
