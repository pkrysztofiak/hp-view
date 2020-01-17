package pl.pkrysztofiak.hpview.model.panels.grid.lines.vertical;

import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineType;

public class GridVLineModel extends GridLineModel {

    public GridVLineModel(double ratioX) {
        super(ratioX);
    }

    @Override
    public GridLineType getType() {
        return GridLineType.VERTICAL;
    }
    
    public Double getRatioX() {
        return ratioPositionProperty.get();
    }
}