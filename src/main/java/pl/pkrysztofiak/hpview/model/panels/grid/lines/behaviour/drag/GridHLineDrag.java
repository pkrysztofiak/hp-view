package pl.pkrysztofiak.hpview.model.panels.grid.lines.behaviour.drag;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.LineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.horizontal.GridHLineModel;

public class GridHLineDrag extends GridLineDragBehaviour {
    
    private double dragStartY;
    private double gridLineDragStartY;
    
    public GridHLineDrag(GridLineModel gridLineModel, ObservableList<GridLineModel> gridLines) {
        super(gridLineModel, gridLines);
    }

    @Override
    protected void onDragStart(Point2D point) {
        dragStartY = point.getY();
        gridLineDragStartY = gridLineModel.getRatioPosition();
        
        dragLines.clear();
        double ratioX = point.getX();
        sortedLines.stream().filter(hLine -> hLine.contains(ratioX)).findFirst().ifPresent(hLine -> {
            dragLines.add(hLine);
            int index = sortedLines.indexOf(hLine);
            for (int i = index, j = i + 1; j < sortedLines.size(); i++, j++) {
                LineModel line = sortedLines.get(i);
                LineModel nextLine = sortedLines.get(j);
                if (line.isConnected(nextLine)) {
                    dragLines.add(nextLine);
                } else {
                    break;
                }
            }
            for (int i = index, j = i - 1; j >= 0; i--, j--) {
                LineModel line = sortedLines.get(i);
                LineModel nextLine = sortedLines.get(j);
                if (line.isConnected(nextLine)) {
                    dragLines.add(nextLine);
                } else {
                    break;
                }
            }
        });
    }

    @Override
    protected void onDrag(Point2D point) {
        if (dragLines.isEmpty()) {
            throw new RuntimeException();
        }
        ObservableList<LineModel> lines = gridLineModel.getLines();
        if (lines.size() != dragLines.size()) {
            ArrayList<LineModel> linesToRemove = new ArrayList<>(lines);
            linesToRemove.removeAll(dragLines);
            lines.removeAll(linesToRemove);
            gridLines.add(new GridHLineModel(gridLineModel.getRatioPosition(), gridLines, linesToRemove.stream().map(LineModel::getPanel).collect(Collectors.toList())));
        }
        double dragY = point.getY();
        double deltaY = dragY - dragStartY;
        gridLineModel.setRatioPosition(gridLineDragStartY + deltaY);
    }

    @Override
    protected void onDragFinish(Point2D point) {
        
    }
}