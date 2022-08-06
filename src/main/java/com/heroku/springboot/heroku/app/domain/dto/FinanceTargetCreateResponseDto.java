package com.heroku.springboot.heroku.app.domain.dto;

import lombok.Data;

@Data
public class FinanceTargetCreateResponseDto {
    private Long id;
    private Long totalOutstanding;
    private Long targetAmount;
    private boolean suggestEnable;
}
