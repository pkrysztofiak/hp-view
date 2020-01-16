package pl.pkrysztofiak.hpview;


import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class ObservableListApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Employee john = new Employee("John");
        Employee mary = new Employee("Mary");
        
//        ObservableList<Employee> employees = FXCollections.observableArrayList(employee -> new Observable[] {employee.nameProperty()});
//        employees.setAll(john, mary);
//        
//        JavaFxObservable.updatesOf(employees).subscribe(employee -> {
//            System.out.println("name=" + employee.getName());
//        });
//        
//        john.setName("Johnn");
        
        ObservableList<ObjectProperty<Employee>> employees = FXCollections.observableArrayList(property -> new Observable[] {property});
        JavaFxObservable.updatesOf(employees).subscribe(employee -> {
            System.out.println("name=" + employee.get().getName());
        });
        
        employees.add(new SimpleObjectProperty<Employee>(john));
        employees.add(new SimpleObjectProperty<Employee>(mary));

        employees.get(0).set(new Employee("Sarah"));
        
    }
}