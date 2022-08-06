package com.heroku.springboot.heroku.app.repository;

import com.heroku.springboot.heroku.app.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAssetRepository extends JpaRepository<AssetEntity, Long> {
}
