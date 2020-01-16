package pl.pkrysztofiak.hpview.model.panels.grid;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.hangingprotocol.panel.HangingProtocolPanel;
import pl.pkrysztofiak.hpview.model.panels.grid.panel.GridPanelModel;

public class GridPanelsModel {

    private final ObservableList<GridPanelModel> gridPanels = FXCollections.observableArrayList();
    
    private final ObjectProperty<HangingProtocolPanel> hpPanelProperty = new SimpleObjectProperty<>();
    private final Observable<HangingProtocolPanel> hpPanelObservable = JavaFxObservable.valuesOf(hpPanelProperty);

    {
        hpPanelObservable.subscribe(this::onHpPanelChanged);
    }
    
    public GridPanelsModel(HangingProtocolPanel hpPanel) {
        hpPanelProperty.set(hpPanel);
    }
    
    private void onHpPanelChanged(HangingProtocolPanel hpPanel) {
        
    }
}