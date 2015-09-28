package me.j360.boot.microservice.order.test.repo;

import me.j360.boot.microservice.order.J360Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.deliver.test.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 16:04
 * 说明：
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = J360Configuration.class)
@WebAppConfiguration
public class ApplicationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testHome() throws Exception {
        this.mvc.perform(get("/api/expresses/1")).andExpect(status().isOk())
                .andExpect(content().string(containsString("100")));
    }

    @Test
    public void byId() throws Exception {
        this.mvc.perform(
                get("/api/expresses/1"))
                .andExpect(status().isOk());
    }



}
