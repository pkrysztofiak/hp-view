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
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.Orientation;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.horizontal.GridHLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.vertical.GridVLineModel;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;

public class GridPanelsModel {
    
    private final ObjectProperty<HpPanel> hpPanelProperty = new SimpleObjectProperty<>();
    private final Observable<HpPanel> hpPanelObservable = JavaFxObservable.valuesOf(hpPanelProperty);

    public final PublishSubject<HpPanel> setHpPanel = PublishSubject.create();
    
    {
        setHpPanel.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(hpPanelProperty::set);
    }
    
    private final ObservableList<GridLineModel> gridLines = FXCollections.observableArrayList();
    
//    private final ObservableList<GridPanelModel> gridPanels = FXCollections.observableArrayList();
    

    {
        hpPanelObservable.subscribe(this::onHpPanelChanged);
        hpPanelObservable.switchMap(hpPanel -> Observable.fromIterable(hpPanel.getPanels())).subscribe(this::onPanelAdded);
        hpPanelObservable.switchMap(HpPanel::panelAddedObservable).subscribe(this::onPanelAdded);
    }
    
    public GridPanelsModel() {
        
    }
    
    private void onHpPanelChanged(HpPanel hpPanel) {
        gridLines.clear();
    }
    
    private void onPanelAdded(PanelModel panel) {
        addGridHLine(panel);
        addGridVLine(panel);
    }
    
    private void addGridHLine(PanelModel panel) {
        Stream.of(panel.getRatioMinY(), panel.getRatioMaxY()).forEach(ratioY -> {
            Optional<GridLineModel> optional = findGridLine(ratioY, Orientation.HORIZONTAL);
            if (optional.isPresent()) {
                GridLineModel gridHLine = optional.get();
                gridHLine.addPanelModel.onNext(panel);
            } else {
                GridHLineModel gridHLine = new GridHLineModel(ratioY, gridLines);
                gridHLine.addPanelModel.onNext(panel);
                gridLines.add(gridHLine);
            }
        });
    }
    
    private void addGridVLine(PanelModel panel) {
        Stream.of(panel.getRatioMinX(), panel.getRatioMaxX()).forEach(ratioX -> {
            Optional<GridLineModel> optional = findGridLine(ratioX, Orientation.VERTICAL);
            if(optional.isPresent()) {
                GridLineModel gridVLine = optional.get();
                gridVLine.addPanelModel.onNext(panel);
            } else {
                GridVLineModel gridVLine = new GridVLineModel(ratioX, gridLines);
                gridVLine.addPanelModel.onNext(panel);
                gridLines.add(gridVLine);
            }
        });
    }
    
    private Optional<GridLineModel> findGridLine(double ratioPosition, Orientation orientation) {
        return gridLines.stream()
                .filter(gridLine -> orientation.equals(gridLine.getOrientation()))
                .filter(gridLine -> gridLine.getRatioPosition().equals(ratioPosition))
                .findFirst();
    }
}