package me.j360.boot.microservice.profile.repository;

import me.j360.boot.microservice.profile.domain.Profile;

import java.util.List;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.profile.repository.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 21:00
 * 说明：
 */
public interface ProfileRepository {

    List<Profile> findAll();

    Profile findOne(Long id);

}
