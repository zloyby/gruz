package by.zloy;

import java.util.Date;

public class HomePage extends TemplatePage {

    public String title = this.getMessage("index.title");

    public HomePage() {
        addModel("time", new Date());
    }
}
