package pl.pkrysztofiak.hpview.model.panels.grid.lines;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;

public abstract class GridLineModel {

    protected final ObservableList<? extends GridLineModel> gridLines;
    
    protected final ObservableList<PanelModel> panels = FXCollections.observableArrayList();
    public final PublishSubject<PanelModel> addPanelModel = PublishSubject.create();

    {
        addPanelModel.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(panels::add);
    }
    
    protected final ObjectProperty<Double> ratioPositionProperty = new SimpleObjectProperty<>();
    
    public GridLineModel(double ratioPosition, ObservableList<? extends GridLineModel> gridLines) {
        ratioPositionProperty.set(ratioPosition);
        this.gridLines = gridLines;
    }
    
    public Double getRatioPosition() {
        return ratioPositionProperty.get();
    }
    
    public abstract Orientation getOrientation();
    
    
}