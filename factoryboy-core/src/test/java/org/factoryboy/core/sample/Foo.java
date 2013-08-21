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

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getCent() {
        return cent;
    }

    public void setCent(Long cent) {
        this.cent = cent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Foo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", age=").append(age);
        sb.append(", cent=").append(cent);
        sb.append('}');
        return sb.toString();
    }
}
