package hello;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Quote {
    @Id
    private String id;

    @NotNull
    @Size(min=2, max=100)
    private String celebrity = "Nike";

    @NotNull
    private String quote = "Just Do It.";

    Quote() {}

    Quote(String celebrity, String quote) {
        this.celebrity = celebrity;
        this.quote = quote;
    }

    public String getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(String celebrity) {
        this.celebrity = celebrity;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return celebrity + " said:\n"
                + "\t " + quote;
    }
}
