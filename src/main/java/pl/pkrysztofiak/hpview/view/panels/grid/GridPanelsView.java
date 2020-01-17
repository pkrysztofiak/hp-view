package pl.pkrysztofiak.hpview.view.panels.grid;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import pl.pkrysztofiak.hpview.model.hp.panel.HpPanel;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;
import pl.pkrysztofiak.hpview.view.panels.grid.lines.GridLineView;

public class GridPanelsView extends Pane {

    private final ObjectProperty<HpPanel> hpPanelProperty = new SimpleObjectProperty<>();
    private final Observable<HpPanel> hpPanelObservable = JavaFxObservable.valuesOf(hpPanelProperty);
    
    private final ObservableList<GridLineView> gridLines = FXCollections.observableArrayList();
    
    {
        hpPanelObservable.subscribe(this::onHpPanelChanged);
    }
    
    public GridPanelsView() {
        
    }
    
    private void onHpPanelChanged(HpPanel hpPanel) {
        gridLines.clear();
        Observable.fromIterable(hpPanel.getPanels()).subscribe(this::onPanelModelAdded);
    }
    
    private void onPanelModelAdded(PanelModel panelModel) {
        
    }
}