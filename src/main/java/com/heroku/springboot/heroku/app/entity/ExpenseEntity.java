package com.heroku.springboot.heroku.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "expense_category")
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "note")
    private String note;

    @Column(name = "is_income")
    private boolean income;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    private List<ExpenseDetailEntity> details;
}
