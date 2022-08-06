package com.heroku.springboot.heroku.app.repository;

import com.heroku.springboot.heroku.app.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICardRepository extends JpaRepository<CardEntity, Long> {
}
