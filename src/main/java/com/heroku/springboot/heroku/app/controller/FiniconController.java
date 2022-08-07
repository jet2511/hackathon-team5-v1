package com.heroku.springboot.heroku.app.controller;

import com.heroku.springboot.heroku.app.domain.dto.FinanceTargetCreateRequestDto;
import com.heroku.springboot.heroku.app.entity.ExpenseEntity;
import com.heroku.springboot.heroku.app.service.IExpenseService;
import com.heroku.springboot.heroku.app.service.IFiniconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/finicon")
public class FiniconController {

    @Autowired
    private IExpenseService expenseService;

    @Autowired
    private IFiniconService finiconService;

    @GetMapping("/expenses")
    public ResponseEntity getAllExpenses() {
        return new ResponseEntity(expenseService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/expenses")
    public ResponseEntity saveProduct(@RequestBody ExpenseEntity entity) {
        return new ResponseEntity(expenseService.save(entity), HttpStatus.CREATED);
    }

    @GetMapping("/dashboard")
    public ResponseEntity getFiniconHomepage() {
        return new ResponseEntity(finiconService.getFiniconHomepage(), HttpStatus.OK);
    }

    @GetMapping("/finance-categories")
    public ResponseEntity getExpenseDetail() {
        return new ResponseEntity(finiconService.getFinanceCategories(), HttpStatus.OK);
    }

    @PostMapping("/finance-details")
    public ResponseEntity addFinanceTargetDetail(@RequestBody FinanceTargetCreateRequestDto requestDto) {
        return new ResponseEntity(finiconService.addFinanceTargetDetail(requestDto), HttpStatus.OK);
    }

    @GetMapping("/finance-details/{id}")
    public ResponseEntity getFinanceDetail(@PathVariable Long id) {
        return new ResponseEntity(finiconService.getFinanceDetail(id), HttpStatus.OK);
    }
}
