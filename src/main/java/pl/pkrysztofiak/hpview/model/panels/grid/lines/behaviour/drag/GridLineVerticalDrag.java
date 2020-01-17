package pl.pkrysztofiak.hpview.model.panels.grid.lines.behaviour.drag;

import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;

public class GridLineVerticalDrag extends GridLineDragBehaviour {

    public GridLineVerticalDrag(GridLineModel gridLine, ObservableList<? extends GridLineModel> gridLines) {
        super(gridLine, gridLines);
    }
}