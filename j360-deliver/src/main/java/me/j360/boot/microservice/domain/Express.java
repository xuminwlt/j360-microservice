package me.j360.boot.microservice.domain;

import java.math.BigDecimal;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.domain.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 14:43
 * 说明：
 */
public class Express {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    private BigDecimal cost;

}
