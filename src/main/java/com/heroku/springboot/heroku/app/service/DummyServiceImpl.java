package com.heroku.springboot.heroku.app.service;

import com.heroku.springboot.heroku.app.entity.DummyEntity;
import com.heroku.springboot.heroku.app.repository.IDummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyServiceImpl implements IDummyService{

    @Autowired
    private IDummyRepository dummyRepository;

    @Override
    public DummyEntity saveDummyEntity(DummyEntity dummyEntity){
        return dummyRepository.save(dummyEntity);
    }

    @Override
    public List<DummyEntity> getAllDummyEntity() {
        return dummyRepository.findAll();
    }

}
