package digi.ecomm.corecustomapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CoreCustomApiApplication extends SpringBootServletInitializer {

    /**
     * Starting point of the application.
     *
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(CoreCustomApiApplication.class, args);
    }
}
