package pl.pkrysztofiak.hpview.model.panels.panel;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class PanelModel {

    public final ObjectProperty<Double> minXProperty = new SimpleObjectProperty<>();
    public final Observable<Double> minXObservable = JavaFxObservable.valuesOf(minXProperty);
    
    public final ObjectProperty<Double> minYProperty = new SimpleObjectProperty<>();
    public final Observable<Double> minYObservable = JavaFxObservable.valuesOf(minYProperty);
    
    public final ObjectProperty<Double> maxXProperty = new SimpleObjectProperty<>();
    public final Observable<Double> maxXObservable = JavaFxObservable.valuesOf(maxXProperty);
    
    public final ObjectProperty<Double> maxYProperty = new SimpleObjectProperty<>();
    public final Observable<Double> maxYObservable = JavaFxObservable.valuesOf(maxYProperty);
    
    public PanelModel(double minX, double minY, double maxX, double maxY) {
        minXProperty.set(minX);
        minYProperty.set(minY);
        maxXProperty.set(maxX);
        maxYProperty.set(maxY);
    }
    
    public void setMinX(double value) {
        minXProperty.set(value);
    }
    
    public Double getMinX() {
        return minXProperty.get();
    }
    
    public void setMinY(double value) {
        minYProperty.set(value);
    }
    
    public Double getMinY() {
        return minYProperty.get();
    }
    
    public void setMaxX(double value) {
        maxXProperty.set(value);
    }
    
    public Double getMaxX() {
        return maxXProperty.get();
    }
    
    public void setMaxY(double value) {
        maxYProperty.set(value);
    }
    
    public Double getMaxY() {
        return maxYProperty.get();
    }
}
