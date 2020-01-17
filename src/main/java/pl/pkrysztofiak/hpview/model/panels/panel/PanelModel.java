package pl.pkrysztofiak.hpview.model.panels.panel;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class PanelModel {

    public final ObjectProperty<Double> ratioMinXProperty = new SimpleObjectProperty<>();
    public final Observable<Double> ratioMinXObservable = JavaFxObservable.valuesOf(ratioMinXProperty);
    
    public final ObjectProperty<Double> ratioMinYProperty = new SimpleObjectProperty<>();
    public final Observable<Double> ratioMinYObservable = JavaFxObservable.valuesOf(ratioMinYProperty);
    
    public final ObjectProperty<Double> ratioMaxXProperty = new SimpleObjectProperty<>();
    public final Observable<Double> ratioMaxXObservable = JavaFxObservable.valuesOf(ratioMaxXProperty);
    
    public final ObjectProperty<Double> ratioMaxYProperty = new SimpleObjectProperty<>();
    public final Observable<Double> ratioMaxYObservable = JavaFxObservable.valuesOf(ratioMaxYProperty);
    
    public PanelModel(double ratioMinX, double ratioMinY, double ratioMaxX, double ratioMaxY) {
        ratioMinXProperty.set(ratioMinX);
        ratioMinYProperty.set(ratioMinY);
        ratioMaxXProperty.set(ratioMaxX);
        ratioMaxYProperty.set(ratioMaxY);
    }
    
    public void setRatioMinX(double value) {
        ratioMinXProperty.set(value);
    }
    
    public Double getRatioMinX() {
        return ratioMinXProperty.get();
    }
    
    public void setRatioMinY(double value) {
        ratioMinYProperty.set(value);
    }
    
    public Double getRatioMinY() {
        return ratioMinYProperty.get();
    }
    
    public void setRatioMaxX(double value) {
        ratioMaxXProperty.set(value);
    }
    
    public Double getRatioMaxX() {
        return ratioMaxXProperty.get();
    }
    
    public void setRatioMaxY(double value) {
        ratioMaxYProperty.set(value);
    }
    
    public Double getRatioMaxY() {
        return ratioMaxYProperty.get();
    }
    
    @Override
    public String toString() {
        return "PanelModel[ratioMinX=" + ratioMinXProperty.get() + ", ratioMinY=" + ratioMinYProperty.get() + ", ratioMaxX=" + ratioMaxXProperty.get() + ", ratioMaxY=" + ratioMaxYProperty.get() + "]";
    }
}
