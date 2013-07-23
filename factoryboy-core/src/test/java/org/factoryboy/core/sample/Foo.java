package org.factoryboy.core.sample;

import java.math.BigDecimal;
import java.util.Date;

/**
 * for Test.
 */
public class Foo {

    private String name;

    private BigDecimal amount;

    private Date createdAt;

    private int age;

    private Long cent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getCent() {
        return cent;
    }

    public void setCent(Long cent) {
        this.cent = cent;
    }
}
