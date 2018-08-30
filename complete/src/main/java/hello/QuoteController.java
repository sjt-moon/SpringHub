package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

/**
 * the URL /quote has 2 operations, get & post
 */
@Controller
public class QuoteController implements WebMvcConfigurer {

    /**
     * Data Access Layer
     */
    private final QuoteDAL quoteDAL;

    @Autowired
    QuoteController(QuoteDAL quoteDAL) {
        super();
        this.quoteDAL = quoteDAL;
    }

    /**
     * map URL to view name
     * @param registry registry pt
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/quote-results").setViewName("quote-results");
    }

    /**
     * Get form, where we could input info
     * @param model in which store input data
     * @return HTML template name, it should be a HTML template under resources directory
     */
    @GetMapping("/quote-submit")
    public String showQuoteForm(Model model) {
        Quote q = new Quote();
        model.addAttribute("qf", q);
        return "quote-form";
    }

    /**
     * check submitted quote validity
     * @param quoteForm submitted data in QuoteForm format
     * @param bindingResult checks whether input data is valid (specified in Quote.class, e.g. @NotNull)
     * @return if invalid, return the original input form to re-enter info; otherwise, go to the result template
     */
    @PostMapping("/quote-submit")
    public String checkQuoteInfo(@Valid @ModelAttribute("qf") Quote quoteForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "quote-form";
        }

        quoteDAL.save(quoteForm);
        System.out.println("find all");
        quoteDAL.findAll().forEach(System.out::println);

        return "quote-results";
    }
}
