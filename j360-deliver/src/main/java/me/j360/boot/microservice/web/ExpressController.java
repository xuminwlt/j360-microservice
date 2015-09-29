package me.j360.boot.microservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.j360.boot.microservice.domain.Express;
import me.j360.boot.microservice.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Map;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.order.web.
 * User: min_xu
 * Date: 2015/9/27
 * Time: 21:07
 *
 */

@Controller
public class ExpressController {

    @Autowired
    private ExpressService expressService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/express",method = RequestMethod.GET)
    public String express(Model model) {
        Map<String,Object>  map = expressService.getMap(1l);
        model.addAttribute("express",map);
        return "index";
    }


}
