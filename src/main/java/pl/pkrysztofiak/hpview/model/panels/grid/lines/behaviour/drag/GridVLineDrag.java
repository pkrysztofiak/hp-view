package pl.pkrysztofiak.hpview.model.panels.grid.lines.behaviour.drag;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.LineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.vertical.GridVLineModel;

public class GridVLineDrag extends GridLineDragBehaviour {

    public GridVLineDrag(GridLineModel gridLine, ObservableList<GridLineModel> gridLines) {
        super(gridLine, gridLines);
    }
    
    private double dragStartX;
    private double gridLineDragStartX;

    @Override
    protected void onDragStart(Point2D point) {
        dragStartX = point.getX();
        gridLineDragStartX = gridLineModel.getRatioPosition();
        
        dragLines.clear();
        double ratioY = point.getY();
        sortedLines.stream().filter(vLine -> vLine.contains(ratioY)).findFirst().ifPresent(vLine -> {
            dragLines.add(vLine);
            int index = sortedLines.indexOf(vLine);
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
            gridLines.add(new GridVLineModel(gridLineModel.getRatioPosition(), gridLines, linesToRemove.stream().map(LineModel::getPanel).collect(Collectors.toList())));
        }
        double dragX = point.getX();
        double deltaX = dragX - dragStartX;
        gridLineModel.setRatioPosition(gridLineDragStartX + deltaX);
    }

    @Override
    protected void onDragFinish(Point2D point) {
        // TODO Auto-generated method stub
        
    }
}