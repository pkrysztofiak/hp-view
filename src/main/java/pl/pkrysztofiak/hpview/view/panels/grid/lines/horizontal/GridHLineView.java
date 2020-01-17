package pl.pkrysztofiak.hpview.view.panels.grid.lines.horizontal;

import io.reactivex.Observable;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.view.panels.grid.GridPanelsView;
import pl.pkrysztofiak.hpview.view.panels.grid.lines.GridLineView;

public class GridHLineView extends GridLineView {

    {
        setMinHeight(0);
        setPrefHeight(0.);
        setMaxHeight(0);
    }
    
    public GridHLineView(GridLineModel gridLineModel, GridPanelsView gridPanelsView) {
        super(gridLineModel, gridPanelsView);
        
        Observable.combineLatest(gridLineModel.ratioPositionObservable(), gridPanelsView.heightObservable(), (ratioY, height) -> ratioY * height).subscribe(this::setLayoutY);
        gridPanelsView.widthObservable().subscribe(this::setPrefWidth);
    }
}