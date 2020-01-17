package pl.pkrysztofiak.hpview.model.panels.grid.lines;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class GridLineModel {

    protected final ObjectProperty<Double> ratioPositionProperty = new SimpleObjectProperty<>();
    
    public GridLineModel(double ratioPosition) {
        ratioPositionProperty.set(ratioPosition);
    }
    
    public abstract GridLineType getType();
    
}