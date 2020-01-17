package pl.pkrysztofiak.hpview.view.panels.grid.lines;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.LineModel;
import pl.pkrysztofiak.hpview.view.panels.grid.GridPanelsView;

public class GridLineView extends Pane {

    protected final GridLineModel gridLineModel;
    protected final GridPanelsView gridPanelsView;
    
    protected final ObservableList<LineView> lines = FXCollections.observableArrayList();
    
    public GridLineView(GridLineModel gridLineModel, GridPanelsView gridPanelsView) {
        this.gridLineModel = gridLineModel;
        this.gridPanelsView = gridPanelsView;

        Observable.fromIterable(gridLineModel.getLines()).observeOn(JavaFxScheduler.platform()).subscribe(this::onLineModelAdded);
        gridLineModel.lineAddedObservable().observeOn(JavaFxScheduler.platform()).subscribe(this::onLineModelAdded);
    }
    
    private void onLineModelAdded(LineModel lineModel) {
        
    }
}