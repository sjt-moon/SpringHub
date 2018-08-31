package hello;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Component
class QuoteList extends VerticalLayout{

    private final QuoteDAL quoteDAL;

    private final List<QuoteLayout> quoteLayoutList = new LinkedList<>();

    @Autowired
    QuoteList(QuoteDAL quoteDAL) {
        this.quoteDAL = quoteDAL;
        this.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }

    @PostConstruct
    void init() {
        setSpacing(true);

        setQuotes(quoteDAL.findAll());
    }

    void setQuotes(List<Quote> quotes) {
        removeAllComponents();

        quoteLayoutList.clear();

        quotes.forEach(e -> {
            QuoteLayout quoteLayout = new QuoteLayout(e);
            quoteLayoutList.add(quoteLayout);
            this.addComponent(quoteLayout);
        });
    }

    public void add(Quote quote) {
        quoteDAL.save(quote);
        update();
    }

    void update() {
        setQuotes(quoteDAL.findAll());
    }

    List<QuoteLayout> getQuoteLayoutList() {
        return quoteLayoutList;
    }
}
