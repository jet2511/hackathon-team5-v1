package com.heroku.springboot.heroku.app.repository;

import com.heroku.springboot.heroku.app.entity.AssetEntity;
import com.heroku.springboot.heroku.app.entity.FinanceTargetCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFinanceCategoryRepository extends JpaRepository<FinanceTargetCategoryEntity, Long> {
}
