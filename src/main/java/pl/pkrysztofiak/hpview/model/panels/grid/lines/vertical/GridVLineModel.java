package pl.pkrysztofiak.hpview.model.panels.grid.lines.vertical;

import java.util.List;

import io.reactivex.Observable;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.LineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.Orientation;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.behaviour.drag.GridVLineDrag;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;

public class GridVLineModel extends GridLineModel {

    public GridVLineModel(double ratioX, ObservableList<GridLineModel> gridLines) {
        super(ratioX, gridLines);
        dragBehaviour = new GridVLineDrag(this, gridLines);
    }
    
    public GridVLineModel(double ratioX, ObservableList<GridLineModel> gridLines, List<PanelModel> panels) {
        super(ratioX, gridLines, panels);
        dragBehaviour = new GridVLineDrag(this, gridLines);
    }

    @Override
    public Orientation getOrientation() {
        return Orientation.VERTICAL;
    }
    
    public Double getRatioX() {
        return ratioPositionProperty.get();
    }

    @Override
    protected LineModel createLine(PanelModel panel) {
        VLineModel vLine = new VLineModel(panel);
        Observable<LineModel> vLineRemoved = lineRemovedObservable.filter(vLine::equals);
        if (panel.getRatioMinX().equals(ratioPositionProperty.get())) {
            ratioPositionObservable.takeUntil(vLineRemoved).subscribe(panel::setRatioMinX);
            panel.ratioMinXObservable.filter(this::notEqualsRatioPosition).takeUntil(vLineRemoved).subscribe(ratioMinX -> lines.remove(vLine));
        } else if (panel.getRatioMaxX().equals(ratioPositionProperty.get())) {
            ratioPositionObservable.takeUntil(vLineRemoved).subscribe(panel::setRatioMaxX);
            panel.ratioMaxXObservable.filter(this::notEqualsRatioPosition).takeUntil(vLineRemoved).subscribe(ratioMaxX -> lines.remove(vLine));
        }
        panel.ratioMinYObservable.takeUntil(vLineRemoved).subscribe(vLine::setRatioStartPosition);
        panel.ratioMaxYObservable.takeUntil(vLineRemoved).subscribe(vLine::setRatioEndPosition);
        return vLine;
    }
}