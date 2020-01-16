package pl.pkrysztofiak.hpview.model.panels.grid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.panels.grid.panel.GridPanelModel;

public class GridPanelsModel {

    private final ObservableList<GridPanelModel> gridPanels = FXCollections.observableArrayList();
    
    public GridPanelsModel(GridPanelModel... gridPanels) {
        this.gridPanels.setAll(gridPanels);
    }
}