package me.j360.boot.microservice.order.web;

import me.j360.boot.microservice.order.entity.Express;
import me.j360.boot.microservice.order.repository.ExpressRepository;
import me.j360.boot.microservice.order.validator.ExpressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.order.web.
 * User: min_xu
 * Date: 2015/9/27
 * Time: 21:07
 * 说明：通过restController实现的restAPI
 */
@RestController
@RequestMapping("/expresses")
public class ExpressController {

    private final ExpressRepository repository;
    private final ExpressValidator validator;

    @Autowired
    public ExpressController(ExpressRepository repository,ExpressValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable findAll() {
        return repository.findAll();
    }
    @RequestMapping(method = RequestMethod.POST)
    public Express create(@RequestBody Express express) {
        return repository.save(express);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Express find(@PathVariable long id) {
        Express detail = repository.findOne(id);
        if (detail == null) {
            throw new ExpressNotFoundException();
        } else {
            return detail;
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
        static class ExpressNotFoundException extends RuntimeException {
    }
}
