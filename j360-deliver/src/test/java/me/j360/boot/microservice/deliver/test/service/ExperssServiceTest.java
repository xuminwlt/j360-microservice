package me.j360.boot.microservice.deliver.test.service;

import me.j360.boot.microservice.J360Configuration;
import me.j360.boot.microservice.api.ExpressApi;
import me.j360.boot.microservice.domain.Express;
import me.j360.boot.microservice.service.ExpressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.deliver.test.service.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 16:04
 * 说明：
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = J360Configuration.class)
public class ExperssServiceTest {

    @Autowired
    private ExpressService expressService;
    @Autowired
    private ExpressApi expressApi;

    /**
     * 使用restapi或许order的map属性
     * 1、expressServiceTest 直接或许
     * 2、expressApiTest 封装hystrix进行读取
     * 3、通过aop-hystrix无侵入式读取
     *
     * */

    @Test
    public void expressServiceTest(){
        Express express = expressService.findOne(1l);
        assertEquals(express.getCost().intValue(),100);
    }

    @Test
    public void expressApiTest(){
        Express express1 = expressApi.getExperss(1l);
    }

    @Test
    public void expressmapTest(){
        Map map = expressService.getMap(1l);
    }

}
