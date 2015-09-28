package me.j360.boot.microservice.order.repository;

import me.j360.boot.microservice.order.entity.Express;
import me.j360.boot.microservice.order.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TrackRepository extends JpaRepository<Track, Long> {

}
