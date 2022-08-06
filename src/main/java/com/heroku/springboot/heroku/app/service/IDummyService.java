package com.heroku.springboot.heroku.app.service;

import com.heroku.springboot.heroku.app.entity.DummyEntity;

import java.util.List;

public interface IDummyService {
    DummyEntity saveDummyEntity(DummyEntity dummyEntity);

    List<DummyEntity> getAllDummyEntity();
}
