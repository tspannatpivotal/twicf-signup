package io.pivotal.thisweekincf;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

/**
 * Created by duncwinn on 12/11/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AdminControllerMvcTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void adminPageIsAccessible() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());

    }

    @Test
    public void expectedFormElementsArePresent() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(xpath("//button[@id='subscriberList']").nodeCount(1));
    }

    /*
    public void postCreatesANewSubscription() throws Exception {
        assertEquals(0, this.subscriptionRepository.findAll().size());

        mockMvc.perform(post("/").param("emailAddress", "testing@example.com"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(xpath("//h1[@id='message']").string("Thanks for subscribing!"));

        assertEquals(1, this.subscriptionRepository.findAll().size());
        assertNotNull(this.subscriptionRepository.findByEmailAddress("testing@example.com"));

    }    */
}
