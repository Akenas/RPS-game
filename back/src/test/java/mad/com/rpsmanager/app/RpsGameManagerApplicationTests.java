package mad.com.rpsmanager.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import mad.com.rpsmanager.infrastructure.config.AppConfiguration;

/**
 * Test class for the Rock Paper Scissors Game Manager application.
 * <p>
 * This class contains tests to verify if the Spring application context loads successfully.
 * </p>
 */
@SpringBootTest
@ActiveProfiles("test")
class RpsGameManagerApplicationTests {

	/**
     * Test to verify that the application context loads without any issues.
     */
	@Test
	void contextLoads() {
	}

}
