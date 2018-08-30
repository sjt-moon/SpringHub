package hello;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class QuoteListEditor extends VerticalLayout {

    private final QuoteDAL quoteDAL;

    /**
     * the currently edited quote
     */
    private Quote quote;

    /**
     * fields to edit properties in Quote entity
     */
    TextField celebrity = new TextField("celebrity");

    /**
     *  action buttons */
    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete");
    final HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private ChangeHandler changeHandler;

    final HorizontalLayout icons = new HorizontalLayout(celebrity, actions);

    @Autowired
    QuoteListEditor(QuoteDAL quoteDAL) {
        super();
        this.quoteDAL = quoteDAL;

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editQuote(quote));
        setVisible(false);
    }

    void save() {
        quoteDAL.save(quote);
        changeHandler.onChange();
    }

    void delete() {
        quoteDAL.delete(quote);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editQuote(Quote q) {
        if (q == null) {
            setVisible(false);
            return;
        }

        final boolean persisted = q.getCelebrity() != null;
        if (persisted) {
            // Find fresh entity for editing
            quote = quoteDAL.findById(q.getId());
        }
        else {
            quote = q;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        // binder.setBean(customer);

        setVisible(true);

        // Focus first name initially
        celebrity.focus();
    }

    public void setChangeHandler(ChangeHandler ch) {
        changeHandler = ch;
    }
}
