package me.j360.boot.microservice.order.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.order.entity.
 * User: min_xu
 * Date: 2015/9/27
 * Time: 21:00
 * 运单追踪
 */

@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    private Express express;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Express getExpress() {
        return express;
    }

    public void setExpress(Express express) {
        this.express = express;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
