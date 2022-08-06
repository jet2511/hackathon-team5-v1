package com.heroku.springboot.heroku.app.service;

import com.heroku.springboot.heroku.app.domain.dto.FinanceCategoryDto;
import com.heroku.springboot.heroku.app.domain.dto.FinanceTargetCreateRequestDto;
import com.heroku.springboot.heroku.app.domain.dto.FiniconHomepageDTO;
import com.heroku.springboot.heroku.app.entity.FinanceTargetDetailEntity;

import java.util.List;

public interface IFiniconService {
    FiniconHomepageDTO getFiniconHomepage();

    List<FinanceCategoryDto> getFinanceCategories();

    FinanceTargetDetailEntity addFinanceTargetDetail(FinanceTargetCreateRequestDto requestDto);

}
