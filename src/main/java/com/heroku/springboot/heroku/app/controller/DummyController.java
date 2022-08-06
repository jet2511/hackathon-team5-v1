package com.heroku.springboot.heroku.app.controller;

import com.heroku.springboot.heroku.app.entity.DummyEntity;
import com.heroku.springboot.heroku.app.service.IDummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/dummy")
public class DummyController {

    @Autowired
    private IDummyService dummyService;

    @PostMapping
    public ResponseEntity saveProduct(@RequestBody DummyEntity dummyEntity){
        return new ResponseEntity(dummyService.saveDummyEntity(dummyEntity), HttpStatus.CREATED);
    }

    @GetMapping("/alls")
    public ResponseEntity getAll(){
        return new ResponseEntity(dummyService.getAllDummyEntity(), HttpStatus.OK);
    }
}
