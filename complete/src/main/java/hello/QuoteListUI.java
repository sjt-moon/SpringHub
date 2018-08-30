package hello;

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
        addForm();
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

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setSpacing(true);
        formLayout.setWidth("80%");

        TextField textField = new TextField();
        textField.setWidth("100%");

        Button addButton = new Button("");
        addButton.setIcon(FontAwesome.PLUS);
        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);

        formLayout.addComponents(textField, addButton);
        formLayout.setExpandRatio(textField, 1);
        layout.addComponent(formLayout);
    }

    private void addQuoteList() {
        System.out.println("add Quote List");

        quoteList.setWidth("80%");
        layout.addComponent(quoteList);
    }

    private void addActionButton() {
    }
}