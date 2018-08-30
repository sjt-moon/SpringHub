package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {

    private final QuoteDAL quoteDAL;

    private static final Logger logger = LoggerFactory.getLogger("AppsDeveloperBlog");

    @Autowired
    Application(QuoteDAL quoteDAL) {
        super();
        this.quoteDAL = quoteDAL;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Start working");
    }
}
