package pl.pkrysztofiak.hpview;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class Employee {

    private final ReadOnlyObjectWrapper<String> nameProperty = new ReadOnlyObjectWrapper<>();
    
    public Employee(String name) {
        nameProperty.set(name);
    }
    
    public void setName(String name) {
        nameProperty.set(name);
    }
    
    public ReadOnlyObjectProperty<String> nameProperty() {
        return nameProperty;
    }

    public String getName() {
        return nameProperty.get();
    }
}
