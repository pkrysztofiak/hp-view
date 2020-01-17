package pl.pkrysztofiak.hpview.model.panels.grid.lines.horizontal;

import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineType;

public class GridHLineModel extends GridLineModel {

    public GridHLineModel(double ratioY) {
        super(ratioY);
    }

    @Override
    public GridLineType getType() {
        return GridLineType.HORIZONTAL;
    }
}