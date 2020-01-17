package pl.pkrysztofiak.hpview.model.panels.grid.lines.horizontal;

import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.Orientation;

public class GridHLineModel extends GridLineModel {

    public GridHLineModel(double ratioY, ObservableList<? extends GridLineModel> gridLines) {
        super(ratioY, gridLines);
    }

    @Override
    public Orientation getOrientation() {
        return Orientation.HORIZONTAL;
    }
    
    public Double getRatioY() {
        return ratioPositionProperty.get();
    }
}