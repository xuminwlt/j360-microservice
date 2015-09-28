package me.j360.boot.microservice.order.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.order.entity.
 * User: min_xu
 * Date: 2015/9/27
 * Time: 21:00
 * ˵����
 */

@Entity
public class Express {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal cost;
    private String no;

    @OneToMany(mappedBy = "express",fetch = FetchType.LAZY)
    @OrderBy("id desc")
    private List<Track> tracks;


    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
