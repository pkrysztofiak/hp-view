package pl.pkrysztofiak.hpview.model.panels;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.sources.Change;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyProperty;
import pl.pkrysztofiak.hpview.model.hp.panel.HpPanel;

public class HpPanelWrapper {
    
    private final ReadOnlyObjectWrapper<HpPanel> hpPanelProperty = new ReadOnlyObjectWrapper<>();
    private final ReadOnlyProperty<HpPanel> readOnlyHpPanelProperty = hpPanelProperty.getReadOnlyProperty();
    private final Observable<Change<HpPanel>> hpPanelChangeObservable = JavaFxObservable.changesOf(hpPanelProperty); 
    
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
    
    public Observable<Change<HpPanel>> hpPanelChangeObservable() {
        return hpPanelChangeObservable;
    }
}
