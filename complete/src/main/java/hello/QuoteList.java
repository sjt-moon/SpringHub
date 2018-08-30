package hello;

import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
class QuoteList extends VerticalLayout {

    private final QuoteDAL quoteDAL;

    @Autowired
    QuoteList(QuoteDAL quoteDAL) {
        this.quoteDAL = quoteDAL;

        System.out.println("QuoteList");
    }

    @PostConstruct
    void init() {
        System.out.println("QuoteList init");

        setSpacing(true);

        setQuotes(quoteDAL.findAll());
    }

    private void setQuotes(List<Quote> quotes) {
        removeAllComponents();

        quotes.forEach(e -> this.addComponent(new QuoteLayout(e)));
    }


}
