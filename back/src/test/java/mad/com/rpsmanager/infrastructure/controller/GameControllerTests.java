package mad.com.rpsmanager.infrastructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import mad.com.rpsmanager.infrastructure.config.GameServiceConfiguration;

/**
 * Test class for {@link GameController}.
 * <p>
 * This class contains tests to verify the functionality of the endpoints in the {@link GameController}.
 * </p>
 */
@WebMvcTest(GameController.class)
@ContextConfiguration(classes = {GameServiceConfiguration.class})
@TestPropertySource(properties = "com.mad.rpsmanager.persistence.enabled=false")
public class GameControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    /**
     * Test to verify that the root endpoint ("/game/modes") returns the correct response.
     *
     * @throws Exception if an error occurs during the request
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void getGameModes_ReturnsCorrectResponse() throws Exception {
        mockMvc.perform(get("/game/modes"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{},{},{},{}]"));
    }
    
}
