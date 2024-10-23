package mad.com.rpsmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling application requests.
 * <p>
 * This class provides an endpoint to verify the basic functionality of the application.
 * </p>
 */
@RestController
public class AppController {
    
    /**
     * Handles GET requests to the root URL ("/").
     *
     * @return a string indicating that the application is working
     */
    @GetMapping("/")
    public String rootController(){
        return "it works";
    }
}
