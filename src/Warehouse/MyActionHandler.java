package Warehouse;

import javafx.event.ActionEvent;
import javafx.event.*;

public class MyActionHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Something just happened!");
    }
}
