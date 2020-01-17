package pl.pkrysztofiak.hpview.model.hp.panel;

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
public class HpPanel {

    private final String id;
    
    public final ObjectProperty<Double> minXProperty = new SimpleObjectProperty<>();
    private final Observable<Double> minXObservable = JavaFxObservable.valuesOf(minXProperty);
    
    public final ObjectProperty<Double> minYProperty = new SimpleObjectProperty<>();
    private final Observable<Double> minYObservable = JavaFxObservable.valuesOf(minYProperty);
    
    public final ObjectProperty<Double> maxXProperty = new SimpleObjectProperty<>();
    private final Observable<Double> maxXObservable = JavaFxObservable.valuesOf(maxXProperty);
    
    public final ObjectProperty<Double> maxYProperty = new SimpleObjectProperty<>();
    private final Observable<Double> maxYObservable = JavaFxObservable.valuesOf(maxYProperty);
    
    private final ObservableList<PanelModel> panels = FXCollections.observableArrayList();
    private final ObservableList<PanelModel> unmodifiablePanels = FXCollections.unmodifiableObservableList(panels);
    private final Observable<PanelModel> panelAddedObservable = JavaFxObservable.additionsOf(unmodifiablePanels);
    private final Observable<PanelModel> panelRemovedObservable = JavaFxObservable.removalsOf(unmodifiablePanels);
    
    public HpPanel(String id, PanelModel... panels) {
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
    
    public Observable<Double> minXObservable() {
        return minXObservable;
    }
    
    public void setMinY(double value) {
        minYProperty.set(value);
    }
    
    @JsonGetter()
    public Double getMinY() {
        return minYProperty.get();
    }
    
    public Observable<Double> minYObservable() {
        return minYObservable;
    }
    
    public void setMaxX(double value) {
        maxXProperty.set(value);
    }
    
    @JsonGetter()
    public Double getMaxX() {
        return maxXProperty.get();
    }
    
    public Observable<Double> maxXObservable() {
        return maxXObservable;
    }
    
    public void setMaxY(double value) {
        maxYProperty.set(value);
    }
    
    @JsonGetter()
    public Double getMaxY() {
        return maxYProperty.get();
    }
    
    public Observable<Double> maxYObservable() {
        return maxYObservable;
    }
    
    public ObservableList<PanelModel> getPanels() {
        return unmodifiablePanels;
    }
    
    public Observable<PanelModel> panelAddedObservable() {
        return panelAddedObservable;
    }
    
    public Observable<PanelModel> panelRemovedObservable() {
        return panelRemovedObservable;
    }
    
    @Override
    public String toString() {
        return "HpPanel[minX=" + minXProperty.get() + ", minY=" + minYProperty.get() + ", maxX=" + maxXProperty.get() + ", maxY=" + maxYProperty.get() + "]";
    }
}