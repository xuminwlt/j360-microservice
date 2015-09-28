package me.j360.boot.microservice.order.repository;

import me.j360.boot.microservice.order.entity.Express;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "expresss", path = "expresses")
public interface ExpressRepository extends JpaRepository<Express, Long> {

    Express findByNoAllIgnoringCase(String no);
}
