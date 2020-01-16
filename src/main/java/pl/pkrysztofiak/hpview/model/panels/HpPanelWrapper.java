package pl.pkrysztofiak.hpview.model.panels;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.sources.Change;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.pkrysztofiak.hpview.model.hp.panel.HpPanel;

public class HpPanelWrapper {
    
    private final ReadOnlyObjectWrapper<HpPanel> hpPanelProperty = new ReadOnlyObjectWrapper<>();
    private final ReadOnlyProperty<HpPanel> readOnlyHpPanelProperty = hpPanelProperty.getReadOnlyProperty();
    private final Observable<Change<HpPanel>> hpPanelChangeObservable = JavaFxObservable.changesOf(hpPanelProperty); 
    
    private final Observable<HpPanel> hpPanelObservable = JavaFxObservable.valuesOf(hpPanelProperty);
    private final Observable<Double> hpPanelMinXObservable = hpPanelObservable.switchMap(HpPanel::minXObservable);
    private final Observable<Double> hpPanelMinYObservable = hpPanelObservable.switchMap(HpPanel::minYObservable);
    private final Observable<Double> hpPanelMaxXObservable = hpPanelObservable.switchMap(HpPanel::maxXObservable);
    private final Observable<Double> hpPanelMaxYObservable = hpPanelObservable.switchMap(HpPanel::maxYObservable);
    
    public final ObjectProperty<Double> minXProperty = new SimpleObjectProperty<>();
    public final Observable<Double> minXObservable = JavaFxObservable.valuesOf(minXProperty);
    
    public final ObjectProperty<Double> minYProperty = new SimpleObjectProperty<>();
    public final Observable<Double> minYObservable = JavaFxObservable.valuesOf(minYProperty);
    
    public final ObjectProperty<Double> maxXProperty = new SimpleObjectProperty<>();
    public final Observable<Double> maxXObservable = JavaFxObservable.valuesOf(maxXProperty);
    
    public final ObjectProperty<Double> maxYProperty = new SimpleObjectProperty<>();
    public final Observable<Double> maxYObservable = JavaFxObservable.valuesOf(maxYProperty);

    {
        hpPanelMinXObservable.subscribe(minXProperty::set);
        hpPanelMinYObservable.subscribe(minYProperty::set);
        hpPanelMaxYObservable.subscribe(maxYProperty::set);
        hpPanelMaxXObservable.subscribe(maxXProperty::set);
    }
    
    public HpPanelWrapper(HpPanel hpPanel) {
        hpPanelProperty.set(hpPanel);
    }
    
    public ReadOnlyProperty<HpPanel> hpPanelProperty() {
        return readOnlyHpPanelProperty;
    }
    
    public HpPanel getHpPanel() {
        return hpPanelProperty.get();
    }
    
    public void setHpPanel(HpPanel hpPanel) {
        hpPanelProperty.set(hpPanel);
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
    
    public Observable<Change<HpPanel>> hpPanelChangeObservable() {
        return hpPanelChangeObservable;
    }
}
