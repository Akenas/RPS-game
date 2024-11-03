package mad.com.rpsmanager.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import mad.com.rpsmanager.infrastructure.config.AppConfiguration;

/**
 * Main application class for the Rock Paper Scissors Game Manager.
 * <p>
 * This class is responsible for bootstrapping the Spring Boot application.
 * It also imports the necessary configuration from {@link AppConfiguration}.
 * </p>
 */
@SpringBootApplication
@Import(AppConfiguration.class)
public class RpsGameManagerApplication {

	/**
     * The main method serves as the entry point for the application.
     *
     * @param args command line arguments
     */
	public static void main(String[] args) {
		SpringApplication.run(RpsGameManagerApplication.class, args);
	}

}
