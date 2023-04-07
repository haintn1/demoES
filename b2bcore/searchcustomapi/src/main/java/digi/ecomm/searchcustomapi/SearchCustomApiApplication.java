package digi.ecomm.searchcustomapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SearchCustomApiApplication extends SpringBootServletInitializer {

    /**
     * Application entry point.
     *
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(SearchCustomApiApplication.class, args);
    }
}
