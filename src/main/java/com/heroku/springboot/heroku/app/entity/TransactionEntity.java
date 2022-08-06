package com.heroku.springboot.heroku.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cardId")
    private Long cardId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "is_income_tran")
    private boolean isIncomeTran;

    @Column(name = "create_date")
    private Timestamp createDate;
}
