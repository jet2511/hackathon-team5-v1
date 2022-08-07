package com.heroku.springboot.heroku.app.service;

import com.heroku.springboot.heroku.app.domain.dto.FinanceCategoryDto;
import com.heroku.springboot.heroku.app.domain.dto.FinanceTargetCreateRequestDto;
import com.heroku.springboot.heroku.app.domain.dto.FinanceTargetCreateResponseDto;
import com.heroku.springboot.heroku.app.domain.dto.FiniconHomepageDTO;

import java.util.List;

public interface IFiniconService {
    FiniconHomepageDTO getFiniconHomepage();

    List<FinanceCategoryDto> getFinanceCategories();

    FinanceTargetCreateResponseDto addFinanceTargetDetail(FinanceTargetCreateRequestDto requestDto);

    FinanceTargetCreateResponseDto getFinanceDetail(Long id);
}
