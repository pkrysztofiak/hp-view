package pl.pkrysztofiak.hpview.model.panels.grid.lines;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;

public abstract class LineModel implements Comparable<LineModel> {
    
    private final ObjectProperty<Double> ratioStartPositionProperty = new SimpleObjectProperty<>();
    private final Observable<Double> ratioStartPositionObservable = JavaFxObservable.valuesOf(ratioStartPositionProperty);
    
    private final ObjectProperty<Double> ratioEndPositionProperty = new SimpleObjectProperty<>();
    private final Observable<Double> ratioEndPositionObservable = JavaFxObservable.valuesOf(ratioEndPositionProperty);
    
    public LineModel(PanelModel panel) {
        
    }
    
    public Double getRatioStartPosition() {
        return ratioStartPositionProperty.get();
    }
    
    public void setRatioStartPosition(double value) {
        ratioStartPositionProperty.set(value);
    }
    
    public Double getRatioEndPosition() {
        return ratioEndPositionProperty.get();
    }
    
    public void setRatioEndPosition(double value) {
        ratioEndPositionProperty.set(value);
    }
    
    public Observable<Double> ratioStartPositionObservable() {
        return ratioStartPositionObservable;
    }
    
    public Observable<Double> ratioEndPositionObservable() {
        return ratioEndPositionObservable;
    }

    @Override
    public int compareTo(LineModel otherLine) {
        int result = ratioStartPositionProperty.get().compareTo(otherLine.getRatioStartPosition());
        if (result == 0) {
            result = ratioEndPositionProperty.get().compareTo(otherLine.getRatioEndPosition());
        }
        return result;
    }
}