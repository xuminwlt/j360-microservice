package me.j360.boot.microservice.service;

import me.j360.boot.microservice.domain.Express;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.service.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 14:45
 * 说明：
 */

public interface ExpressService {

    List<Express> findAll();

    Express findOne(Long id);

    public Map getMap(Long id);
}
