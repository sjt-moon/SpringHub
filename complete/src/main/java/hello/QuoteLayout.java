package hello;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

class QuoteLayout extends HorizontalLayout {

    private final CheckBox done;
    private final TextField text;

    private final Quote quote;

    QuoteLayout(Quote quote) {

        this.quote = quote;

        setSpacing(true);
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        done = new CheckBox();
        text = new TextField();
        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        text.setWidth("800px");

        Binder<Quote> binder = new Binder<>();
        binder.setBean(quote);
        binder.readBean(quote);

        /*
        * YOU NEED TO BIND TO A COMPONENT, OTHERWISE NOTHING COULD BE RENDERED */
        binder.forField(text).bind(Quote::toString, Quote::setQuote);

        addComponents(done, text);
        setExpandRatio(text, 1);
    }

    boolean getCheckBoxValue() {
        return done.getValue();
    }

    Quote getQuote() {
        return quote;
    }
}
