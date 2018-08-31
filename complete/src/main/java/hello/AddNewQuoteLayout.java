package hello;

import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import sun.management.snmp.jvminstr.NotificationTarget;

public class AddNewQuoteLayout extends HorizontalLayout {

    private final QuoteDAL quoteDAL;

    private final TextField celebrityField = new TextField();

    private final TextField quoteField = new TextField();

    private final Button addButton = new Button();

    private final static String CELEBRITY_DEFAULT = "celebrity name";

    private final static String QUOTE_DEFAULT = "quote";

    @Autowired
    AddNewQuoteLayout(QuoteDAL quoteDAL) {

        this.quoteDAL = quoteDAL;

        celebrityField.setValue(CELEBRITY_DEFAULT);
        celebrityField.setWidth("100%");
        celebrityField.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

        quoteField.setValue(QUOTE_DEFAULT);
        quoteField.setWidth("100%");
        quoteField.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

        addButton.setIcon(FontAwesome.PLUS);
        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);

        addButton.addClickListener(event -> add());

        this.addComponents(celebrityField, quoteField, addButton);
        this.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }

    private void add() {
        if (check()) {
            Quote quote = new Quote(celebrityField.getValue(), quoteField.getValue());
            if (quoteDAL.exist(quote)) {
                Notification.show("This quote is already in the database.");
            }
            else {
                Notification.show("Saved.");
                quoteDAL.save(quote);
            }
            celebrityField.setValue(CELEBRITY_DEFAULT);
            quoteField.setValue(QUOTE_DEFAULT);
        }
    }

    private boolean check() {
        return true;
    }
}
