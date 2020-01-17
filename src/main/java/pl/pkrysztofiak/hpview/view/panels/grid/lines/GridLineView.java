package pl.pkrysztofiak.hpview.view.panels.grid.lines;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.LineModel;
import pl.pkrysztofiak.hpview.view.panels.grid.GridPanelsView;
import pl.pkrysztofiak.hpview.view.panels.grid.lines.behaviour.drag.GridLineDragBehaviour;

public abstract class GridLineView extends Pane {

    protected final GridLineModel gridLineModel;
    protected final GridPanelsView gridPanelsView;
    
    protected final ObservableList<LineView> lines = FXCollections.observableArrayList();
    protected final Observable<LineView> lineRemovedObservable = JavaFxObservable.removalsOf(lines);

    private final GridLineDragBehaviour dragBehaviour;
    
    public GridLineView(GridLineModel gridLineModel, GridPanelsView gridPanelsView) {
        this.gridLineModel = gridLineModel;
        this.gridPanelsView = gridPanelsView;
        
        Bindings.bindContent(getChildren(), lines);

        Observable.fromIterable(gridLineModel.getLines()).observeOn(JavaFxScheduler.platform()).subscribe(this::onLineModelAdded);
        gridLineModel.lineAddedObservable().observeOn(JavaFxScheduler.platform()).subscribe(this::onLineModelAdded);
        
        dragBehaviour = new GridLineDragBehaviour(this, gridLineModel, gridPanelsView);
    }
    
    private void onLineModelAdded(LineModel lineModel) {
        LineView lineView = createLineView(lineModel);
        lines.add(lineView);
    }
    
    protected abstract LineView createLineView(LineModel lineModel);
}