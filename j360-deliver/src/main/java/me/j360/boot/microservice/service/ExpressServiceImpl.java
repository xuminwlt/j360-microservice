package me.j360.boot.microservice.service;

import me.j360.boot.microservice.domain.Express;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.service.
 * User: min_xu
 * Date: 2015/9/27
 * Time: 21:32
 * 说明：
 */
@Repository
public class ExpressServiceImpl implements ExpressService{
    private final RestTemplate restTemplate;

    @Autowired
    public ExpressServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private final static String url = "http://localhost:8080/expresses/";

    @Override
    public List<Express> findAll() {
        return null;
    }

    @Override
    public Express findOne(Long id) {
        ResponseEntity<Express> entity =  restTemplate.getForEntity(url+id.toString(),Express.class);
        return entity.getBody();
    }

    @Override
    public Map getMap(Long id) {
        ResponseEntity<Express> entity =  restTemplate.getForEntity("http://localhost:8080/expresses/1",Express.class);
        Express express = entity.getBody();
        Map<String,Object> map = new HashMap<>();
        map.put("cost",express.getCost());
        return map;
    }
}
