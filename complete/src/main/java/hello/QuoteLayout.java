package hello;

import com.vaadin.data.Binder;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

class QuoteLayout extends HorizontalLayout {

    private final CheckBox done;
    private final TextField text;

    QuoteLayout(Quote quote) {

        System.out.println("AVX: " + quote.toString());

        setSpacing(true);
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        done = new CheckBox();
        text = new TextField();
        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        text.setWidth("100%");

        Binder<Quote> binder = new Binder<>();
        binder.setBean(quote);
        binder.readBean(quote);

        addComponents(done, text);
        setExpandRatio(text, 1);
    }
}
