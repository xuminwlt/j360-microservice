package me.j360.boot.microservice.order.repository;

import me.j360.boot.microservice.order.entity.Express;

import java.util.List;


public interface ExpressRepository {

	List<Express> findAll();

}
