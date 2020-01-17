package pl.pkrysztofiak.hpview.model.panels.grid;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.hp.panel.HpPanel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.horizontal.GridHLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.vertical.GridVLineModel;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;

public class GridPanelsModel {

    public final PublishSubject<HpPanel> setHpPanel = PublishSubject.create();
    
    private final ObservableList<GridHLineModel> gridHLines = FXCollections.observableArrayList();
    private final ObservableList<GridVLineModel> gridVLines = FXCollections.observableArrayList();
    
//    private final ObservableList<GridPanelModel> gridPanels = FXCollections.observableArrayList();
    
    private final ObjectProperty<HpPanel> hpPanelProperty = new SimpleObjectProperty<>();
    private final Observable<HpPanel> hpPanelObservable = JavaFxObservable.valuesOf(hpPanelProperty);

    {
        setHpPanel.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(hpPanelProperty::set);
        hpPanelObservable.subscribe(this::onHpPanelChanged);
        hpPanelObservable.switchMap(HpPanel::panelAddedObservable).subscribe(this::onPanelAdded);
    }
    
    public GridPanelsModel() {
        
    }
    
    private void onHpPanelChanged(HpPanel hpPanel) {
        gridHLines.clear();
        gridVLines.clear();
        Observable.fromIterable(hpPanel.getPanels()).subscribe(this::onPanelAdded);
    }
    
    private void onPanelAdded(PanelModel panel) {
        Stream.of(panel.getRatioMinX(), panel.getRatioMaxX()).forEach(ratioX -> {
            
        });
    }
    
    private Optional<GridVLineModel> findGridVLine(double x) {
        return gridVLines.stream().filter(gridVLine -> gridVLine.getRatioX().equals(x)).findFirst();
    }
}