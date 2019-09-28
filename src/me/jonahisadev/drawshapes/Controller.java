package me.jonahisadev.drawshapes;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

public class Controller {

    @FXML
    private TableView points;

    @FXML
    private Button addPoint;

    @FXML
    private TextField xInput;

    @FXML
    private TextField yInput;

    @FXML
    private Polygon shape;

    @FXML
    public void initialize() {
        TableColumn<String, Point> xcol = new TableColumn<>("X");
        xcol.setCellValueFactory(new PropertyValueFactory<>("x"));

        TableColumn<String, Point> ycol = new TableColumn<>("Y");
        ycol.setCellValueFactory(new PropertyValueFactory<>("y"));

        points.getColumns().add(xcol);
        points.getColumns().add(ycol);

        points.setEditable(true);
        points.setPlaceholder(new Label("No points!"));
    }

    private void addPoint() {
        int x = Integer.parseInt(xInput.getText());
        int y = Integer.parseInt(yInput.getText());
        points.getItems().add(new Point(x, y));
        xInput.setText("");
        yInput.setText("");
        xInput.requestFocus();

        updateShape();
    }

    private void removePoint() {
        points.getItems().remove(points.getSelectionModel().getSelectedIndex());
        updateShape();
    }

    private void updateShape() {
        if (points.getItems().size() >= 3) {
            shape.getPoints().remove(0, shape.getPoints().size());

            for (int i = 0; i < points.getItems().size(); i++) {
                Point p = (Point) points.getItems().get(i);
                shape.getPoints().add((double)p.getX());
                shape.getPoints().add((double)p.getY());
            }
        } else {
            shape.getPoints().remove(0, shape.getPoints().size());
        }
    }

    public void addPointAction(MouseEvent mouseEvent) {
        addPoint();
    }

    public void onInputEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER)
            addPoint();
    }

    public void removePointAction(MouseEvent mouseEvent) {
        removePoint();
    }

    public void onTableKey(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.DELETE
                || keyEvent.getCode() == KeyCode.BACK_SPACE) {
            removePoint();
        }
    }
}
