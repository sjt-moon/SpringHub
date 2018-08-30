package hello;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private final String[] vocabulary = {"love", "peace", "hiphop", "fate", "dragon"};
    private final Random rand = new Random();

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam Map<String, String > paramMap) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, paramMap.getOrDefault("name", "love")));
    }

//    @RequestMapping("schedule")
//    @Scheduled(fixedRate = 3000)
//    public void f() {
//        RestTemplate restTemplate = new RestTemplate();
//        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//        log.info(quote.toString());
//    }
}
