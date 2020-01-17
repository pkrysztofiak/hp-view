package pl.pkrysztofiak.hpview.model.panels.grid.lines.behaviour.drag;

import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;

public class GridLineHorizontalDrag extends GridLineDragBehaviour {

    public GridLineHorizontalDrag(GridLineModel gridLineModel, ObservableList<? extends GridLineModel> gridLines) {
        super(gridLineModel, gridLines);
    }

}
