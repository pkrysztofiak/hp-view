package pl.pkrysztofiak.hpview.model.panels.grid.lines;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;

public abstract class GridLineModel {

    protected final ObservableList<? extends GridLineModel> gridLines;
    
    protected final ObservableList<LineModel> lines = FXCollections.observableArrayList();
    protected final ObservableList<LineModel> unmodifiableLines = FXCollections.unmodifiableObservableList(lines);
    protected final Observable<LineModel> lineAddedObservable = JavaFxObservable.additionsOf(lines);
    protected final Observable<LineModel> lineRemovedObservable = JavaFxObservable.removalsOf(lines);
    
    protected final ObservableList<PanelModel> panels = FXCollections.observableArrayList();
    private final Observable<PanelModel> panelAddedObservable = JavaFxObservable.additionsOf(panels);
    
    public final PublishSubject<PanelModel> addPanelModel = PublishSubject.create();

    protected final ObjectProperty<Double> ratioPositionProperty = new SimpleObjectProperty<>();
    protected final Observable<Double> ratioPositionObservable = JavaFxObservable.valuesOf(ratioPositionProperty);
    
    {
        //interface
        addPanelModel.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(panels::add);
    }
    
    {
        //local
        panelAddedObservable.subscribe(this::onPanelAdded);
    }
    
    
    public GridLineModel(double ratioPosition, ObservableList<? extends GridLineModel> gridLines) {
        ratioPositionProperty.set(ratioPosition);
        this.gridLines = gridLines;
    }
    
    public Double getRatioPosition() {
        return ratioPositionProperty.get();
    }

    private void onPanelAdded(PanelModel panel) {
        LineModel line = createLine(panel);
        lines.add(line);
    }
    
    protected abstract LineModel createLine(PanelModel panel);
    
    public abstract Orientation getOrientation();
    
    protected boolean equalsRatioPosition(Double ratioPosition) {
        return ratioPosition.equals(ratioPositionProperty.get());
    }
    
    protected boolean notEqualsRatioPosition(Double ratioPosition) {
        return !equalsRatioPosition(ratioPosition);
    }
    
    public ObservableList<LineModel> getLines() {
        return unmodifiableLines;
    }
    
    public Observable<Double> ratioPositionObservable() {
        return ratioPositionObservable;
    }
    
    public Observable<LineModel> lineAddedObservable() {
        return lineAddedObservable;
    }
    
    public Observable<LineModel> lineRemovedObservable() {
        return lineRemovedObservable;
    }
}