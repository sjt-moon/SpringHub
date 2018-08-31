package hello;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/quote-list")
public class QuoteListUI extends UI {

    @Autowired
    private QuoteDAL quoteDAL;

    private VerticalLayout layout;

    @Autowired
    private QuoteList quoteList;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        searchForm();
        addQuoteList();
        addActionButton();
    }

    private void setupLayout() {
        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    private void addHeader() {
        Label header = new Label("Quote");
        header.addStyleName(ValoTheme.LABEL_H1);
        header.setSizeUndefined();
        layout.addComponent(header);
    }

    /**
     * input text & search quotes for that celebrity (input str)
     */
    private void searchForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setSpacing(true);
        formLayout.setWidth("80%");

        TextField textField = new TextField();
        textField.setWidth("100%");
        textField.setValue("select which celebrity you are searching for XD");

        Button selectButton = new Button("");
        selectButton.setIcon(FontAwesome.SEARCH);
        selectButton.addStyleName(ValoTheme.BUTTON_PRIMARY);

        formLayout.addComponents(textField, selectButton);
        formLayout.setExpandRatio(textField, 1);
        layout.addComponent(formLayout);

        /*
        * select all the quotes for that celebrity */
        selectButton.addClickListener(click -> quoteList.setQuotes(quoteDAL.findAllByCelebrity(textField.getValue())));

        selectButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    private void addQuoteList() {
        quoteList.setWidth("80%");
        layout.addComponent(quoteList);
    }

    private void addActionButton() {
        Button deleteButton = new Button();
        deleteButton.setIcon(FontAwesome.REMOVE);
        deleteButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        deleteButton.setVisible(true);

        layout.addComponent(deleteButton);

        deleteButton.addClickListener(event -> {
            quoteList.getQuoteLayoutList().stream().filter(QuoteLayout::getCheckBoxValue).forEach(x -> {
                quoteDAL.delete(x.getQuote());
            });
            quoteList.update();
        });
    }
}