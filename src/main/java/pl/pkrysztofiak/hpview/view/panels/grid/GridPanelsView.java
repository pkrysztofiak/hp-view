package pl.pkrysztofiak.hpview.view.panels.grid;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import pl.pkrysztofiak.hpview.model.panels.grid.GridPanelsModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.view.panels.grid.lines.GridLineView;
import pl.pkrysztofiak.hpview.view.panels.grid.lines.horizontal.GridHLineView;
import pl.pkrysztofiak.hpview.view.panels.grid.lines.vertical.GridVLineView;

public class GridPanelsView extends Pane {

    private final Observable<Double> widthObservable = JavaFxObservable.valuesOf(widthProperty()).map(Number::doubleValue);
    private final Observable<Double> heightObservable = JavaFxObservable.valuesOf(heightProperty()).map(Number::doubleValue);
    
    private final ObservableList<GridLineView> gridLines = FXCollections.observableArrayList();
    private final Observable<GridLineView> gridLineRemovedObservable = JavaFxObservable.removalsOf(gridLines);
    
    public GridPanelsView(GridPanelsModel gridPanelsModel) {
        Bindings.bindContent(getChildren(), gridLines);
        
        Observable.fromIterable(gridPanelsModel.getGridLines()).delay(0, TimeUnit.SECONDS, JavaFxScheduler.platform()).subscribe(this::onGridLineModelAdded);
        gridPanelsModel.gridLineAddedObservable().delay(0, TimeUnit.SECONDS, JavaFxScheduler.platform()).subscribe(this::onGridLineModelAdded);
    }
    
    private void onGridLineModelAdded(GridLineModel gridLineModel) {
        switch (gridLineModel.getOrientation()) {
        case HORIZONTAL:
            GridHLineView gridHLineView = createGridHLineView(gridLineModel);
            gridLines.add(gridHLineView);
            break;
        case VERTICAL:
            GridVLineView gridVLine = createGridVLineView(gridLineModel);
            gridLines.add(gridVLine);
            break;
        default:
            throw new RuntimeException();
        }
    }
    
    private GridHLineView createGridHLineView(GridLineModel gridLineModel) {
        GridHLineView gridHLine = new GridHLineView(gridLineModel, this);
        
        Observable<GridLineView> gridHLineRemoved = gridLineRemovedObservable.filter(gridHLine::equals);
        
        Observable.combineLatest(gridLineModel.ratioPositionObservable(), heightObservable, (ratioY, height) -> ratioY * height).takeUntil(gridHLineRemoved).subscribe(gridHLine::setLayoutY);
        widthObservable.takeUntil(gridHLineRemoved).subscribe(gridHLine::setPrefWidth);
        return gridHLine;
    }
    
    private GridVLineView createGridVLineView(GridLineModel gridLineModel) {
        GridVLineView gridVLine = new GridVLineView(gridLineModel, this);
        
        Observable<GridLineView> gridVLineRemoved = gridLineRemovedObservable.filter(gridVLine::equals);
        
        Observable.combineLatest(gridLineModel.ratioPositionObservable(), widthObservable, (ratioX, width) -> ratioX * width).takeUntil(gridVLineRemoved).subscribe(gridVLine::setLayoutX);
        heightObservable.takeUntil(gridVLineRemoved).subscribe(gridVLine::setPrefHeight);
        return gridVLine;
    }
    
    public Observable<Double> widthObservable() {
        return widthObservable;
    }
    
    public Observable<Double> heightObservable() {
        return heightObservable;
    }
}