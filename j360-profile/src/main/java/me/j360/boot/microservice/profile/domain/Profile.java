package me.j360.boot.microservice.profile.domain;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.profile.domain.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 20:59
 * 说明：
 */
public class Profile {
    private final Long id;

    private final String firstName;

    private final String lastName;

    public Profile(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
