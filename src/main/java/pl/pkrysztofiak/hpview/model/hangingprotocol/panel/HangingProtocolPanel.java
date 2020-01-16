package pl.pkrysztofiak.hpview.model.hangingprotocol.panel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonGetter;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;

@JsonAutoDetect(fieldVisibility = Visibility.NONE)
public class HangingProtocolPanel {

    private final String id;
    
    public final ObjectProperty<Double> minXProperty = new SimpleObjectProperty<>();
    public final Observable<Double> minXObservable = JavaFxObservable.valuesOf(minXProperty);
    
    public final ObjectProperty<Double> minYProperty = new SimpleObjectProperty<>();
    public final Observable<Double> minYObservable = JavaFxObservable.valuesOf(minYProperty);
    
    public final ObjectProperty<Double> maxXProperty = new SimpleObjectProperty<>();
    public final Observable<Double> maxXObservable = JavaFxObservable.valuesOf(maxXProperty);
    
    public final ObjectProperty<Double> maxYProperty = new SimpleObjectProperty<>();
    public final Observable<Double> maxYObservable = JavaFxObservable.valuesOf(maxYProperty);
    
    private final ObservableList<PanelModel> panels = FXCollections.observableArrayList();
    
    public HangingProtocolPanel(String id, PanelModel... panels) {
        this.id = id;
        this.panels.setAll(panels);
    }
    
    @JsonGetter()
    public String getId() {
        return id;
    }

    public void setMinX(double value) {
        minXProperty.set(value);
    }
    
    @JsonGetter()
    public Double getMinX() {
        return minXProperty.get();
    }
    
    public void setMinY(double value) {
        minYProperty.set(value);
    }
    
    @JsonGetter()
    public Double getMinY() {
        return minYProperty.get();
    }
    
    public void setMaxX(double value) {
        maxXProperty.set(value);
    }
    
    @JsonGetter()
    public Double getMaxX() {
        return maxXProperty.get();
    }
    
    public void setMaxY(double value) {
        maxYProperty.set(value);
    }
    
    @JsonGetter()
    public Double getMaxY() {
        return maxYProperty.get();
    }
}