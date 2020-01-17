package pl.pkrysztofiak.hpview.view.panels.grid.lines.vertical;

import io.reactivex.Observable;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.LineModel;
import pl.pkrysztofiak.hpview.view.panels.grid.GridPanelsView;
import pl.pkrysztofiak.hpview.view.panels.grid.lines.GridLineView;
import pl.pkrysztofiak.hpview.view.panels.grid.lines.LineView;

public class GridVLineView extends GridLineView {

    {
        setMinWidth(0);
        setPrefWidth(0.);
        setMaxWidth(0);
    }
    
    public GridVLineView(GridLineModel gridLineModel, GridPanelsView gridPanelsView) {
        super(gridLineModel, gridPanelsView);
    }

    @Override
    protected LineView createLineView(LineModel lineModel) {
        VLineView vLine = new VLineView();

        Observable<LineView> vLineRemoved = lineRemovedObservable.filter(vLine::equals);
        
        Observable.combineLatest(lineModel.ratioStartPositionObservable(), gridPanelsView.heightObservable(), (ratioStartPosition, height) -> ratioStartPosition * height)
        .takeUntil(vLineRemoved)
        .subscribe(vLine::setStartY);
        
        Observable.combineLatest(lineModel.ratioEndPositionObservable(), gridPanelsView.heightObservable(), (ratioEndPosition, height) -> ratioEndPosition * height)
        .takeUntil(vLineRemoved)
        .subscribe(vLine::setEndY);
        
        return vLine;
    }

}