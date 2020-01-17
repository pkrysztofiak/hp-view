package pl.pkrysztofiak.hpview.model.panels.grid.lines.vertical;

import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.Orientation;

public class GridVLineModel extends GridLineModel {

    public GridVLineModel(double ratioX, ObservableList<? extends GridLineModel> gridLines) {
        super(ratioX, gridLines);
    }

    @Override
    public Orientation getOrientation() {
        return Orientation.VERTICAL;
    }
    
    public Double getRatioX() {
        return ratioPositionProperty.get();
    }
}