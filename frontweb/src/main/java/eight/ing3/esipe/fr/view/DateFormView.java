package eight.ing3.esipe.fr.view;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class DateFormView {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate valueDate;

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }
}
