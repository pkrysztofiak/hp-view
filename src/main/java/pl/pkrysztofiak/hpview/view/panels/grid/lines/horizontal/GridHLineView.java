package pl.pkrysztofiak.hpview.view.panels.grid.lines.horizontal;

import io.reactivex.Observable;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.LineModel;
import pl.pkrysztofiak.hpview.view.panels.grid.GridPanelsView;
import pl.pkrysztofiak.hpview.view.panels.grid.lines.GridLineView;
import pl.pkrysztofiak.hpview.view.panels.grid.lines.LineView;

public class GridHLineView extends GridLineView {

    {
        setMinHeight(0);
        setPrefHeight(0.);
        setMaxHeight(0);
    }
    
    public GridHLineView(GridLineModel gridLineModel, GridPanelsView gridPanelsView) {
        super(gridLineModel, gridPanelsView);
    }

    @Override
    protected LineView createLineView(LineModel lineModel) {
        HLineView hLine = new HLineView();
        
        Observable<LineModel> hLineModelRemovedObservable = gridLineModel.lineRemovedObservable().filter(lineModel::equals);
        hLineModelRemovedObservable.take(1).subscribe(removedLineModel -> lines.remove(hLine));
        
        Observable.combineLatest(lineModel.ratioStartPositionObservable(), gridPanelsView.widthObservable(), (ratioStartPosition, width) -> ratioStartPosition * width)
        .takeUntil(hLineModelRemovedObservable)
        .subscribe(hLine::setStartX);
        
        Observable.combineLatest(lineModel.ratioEndPositionObservable(), gridPanelsView.widthObservable(), (ratioStartPosition, width) -> ratioStartPosition * width)
        .takeUntil(hLineModelRemovedObservable)
        .subscribe(hLine::setEndX);
        return hLine;
    }
}