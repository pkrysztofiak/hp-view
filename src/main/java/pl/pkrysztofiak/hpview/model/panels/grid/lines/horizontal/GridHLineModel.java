package pl.pkrysztofiak.hpview.model.panels.grid.lines.horizontal;

import java.util.List;

import io.reactivex.Observable;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.LineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.Orientation;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.behaviour.drag.GridHLineDrag;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;

public class GridHLineModel extends GridLineModel {

    public GridHLineModel(double ratioY, ObservableList<GridLineModel> gridLines) {
        super(ratioY, gridLines);
        dragBehaviour = new GridHLineDrag(this, gridLines);
    }
    
    public GridHLineModel(double ratioY, ObservableList<GridLineModel> gridLines, List<PanelModel> panels) {
        super(ratioY, gridLines, panels);
        dragBehaviour = new GridHLineDrag(this, gridLines);
    }

    @Override
    public Orientation getOrientation() {
        return Orientation.HORIZONTAL;
    }
    
    public Double getRatioY() {
        return ratioPositionProperty.get();
    }

    @Override
    protected LineModel createLine(PanelModel panel) {
        HLineModel hLine = new HLineModel(panel);
        Observable<LineModel> hLineRemoved = lineRemovedObservable.filter(hLine::equals);
        if (panel.getRatioMinY().equals(ratioPositionProperty.get())) {
            ratioPositionObservable.takeUntil(hLineRemoved).subscribe(panel::setRatioMinY);
            panel.ratioMinYObservable.filter(this::notEqualsRatioPosition).takeUntil(hLineRemoved).subscribe(ratioMinY -> lines.remove(hLine));
        } else if (panel.getRatioMaxY().equals(ratioPositionProperty.get())) {
            ratioPositionObservable.takeUntil(hLineRemoved).subscribe(panel::setRatioMaxY);
            panel.ratioMaxYObservable.filter(this::notEqualsRatioPosition).takeUntil(hLineRemoved).subscribe(ratioMaxY -> lines.remove(hLine));
        } else {
            throw new RuntimeException();
        }
        panel.ratioMinXObservable.takeUntil(hLineRemoved).subscribe(hLine::setRatioStartPosition);
        panel.ratioMaxXObservable.takeUntil(hLineRemoved).subscribe(hLine::setRatioEndPosition);
        return hLine;
    }
}