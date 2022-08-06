package com.heroku.springboot.heroku.app.domain.dto;

import lombok.Data;

@Data
public class AssetDto {
    private Long id;
    private String assetName;
    private Long assetAmount;
}
