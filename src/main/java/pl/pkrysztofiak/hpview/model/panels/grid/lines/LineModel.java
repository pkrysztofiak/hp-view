package pl.pkrysztofiak.hpview.model.panels.grid.lines;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;

public abstract class LineModel {

    private final ObjectProperty<Double> ratioStartPositionProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Double> ratioEndPositionProperty = new SimpleObjectProperty<>();
    
    public LineModel(PanelModel panel) {
        
    }
    
    public Double getRatioStartPosition() {
        return ratioStartPositionProperty.get();
    }
    
    public void setRatioStartPosition(double value) {
        ratioStartPositionProperty.set(value);
    }
    
    public Double getRatioEndPosition() {
        return ratioEndPositionProperty.get();
    }
    
    public void setRatioEndPosition(double value) {
        ratioEndPositionProperty.set(value);
    }
}