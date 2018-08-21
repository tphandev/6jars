package com.petproject.jars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "incomes")
public class Income extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    @Column(columnDefinition = "text")
    private String note;

    @Temporal(TemporalType.DATE)
    @Column(name = "income_date",nullable = false)
    private Date incomeDate;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }
}
