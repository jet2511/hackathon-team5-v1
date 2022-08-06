package com.heroku.springboot.heroku.app.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "expense_detail")
public class ExpenseDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExpenseEntity expense;

    @Column(name="amount")
    private Long amount;

    @Column(name= "note")
    private String note;

    @Column(name= "create_date")
    private Timestamp createDate;
}
