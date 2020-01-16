package pl.pkrysztofiak.hpview.model.panels.grid;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.hp.panel.HpPanel;
import pl.pkrysztofiak.hpview.model.panels.grid.panel.GridPanelModel;

public class GridPanelsModel {

    private final ObservableList<GridPanelModel> gridPanels = FXCollections.observableArrayList();
    
    private final ObjectProperty<HpPanel> hpPanelProperty = new SimpleObjectProperty<>();
    private final Observable<HpPanel> hpPanelObservable = JavaFxObservable.valuesOf(hpPanelProperty);

    {
        hpPanelObservable.subscribe(this::onHpPanelChanged);
    }
    
    public GridPanelsModel(HpPanel hpPanel) {
        hpPanelProperty.set(hpPanel);
    }
    
    private void onHpPanelChanged(HpPanel hpPanel) {
        
    }
}