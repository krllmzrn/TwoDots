package com.example.twodots;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.*;

public class MediumLvl {
    @FXML
    public void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        Scene scene = home.getScene();
        scene.setRoot(root);
    }
    @FXML
    public void restart() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("medium-lvl.fxml"));
        Parent root = loader.load();
        Scene scene = restart.getScene();
        scene.setRoot(root);
    }
    @FXML
    private GridPane gridPane;
    @FXML
    private Pane win,lose;
    @FXML
    private Button home;
    @FXML
    private Label Moves, Blue,Yellow,Red,Green;
    private int moves=30;
    private int blue,red,yellow,green=0;
    @FXML
    private Button restart;



    private Circle startCircle;
    int rows = 5;
    int columns = 5;
    private int[][] field = new int[][]{
            {3, 3, 4, 3, 3},
            {3, 2, 4, 2, 3},
            {3, 2, 4, 2, 3},
            {2, 2, 4, 2, 2},
            {1, 1, 1, 1, 1}
    };
    List<Line> lines = new ArrayList<>();
    List<Circle> circles = new ArrayList<>();
    Paint color;




    @FXML
    private void onMousePressed(MouseEvent e) {
        startCircle = (Circle) e.getPickResult().getIntersectedNode();
        circles.add(startCircle);
    }

    @FXML
    private void onMouseDragged(MouseEvent e){
        Node node = e.getPickResult().getIntersectedNode();
        if (node instanceof Circle && node !=startCircle ) {
            if (!circles.contains(node) && ((Circle) node).getFill()==startCircle.getFill()) {
                int clickedRow = gridPane.getRowIndex(node);
                int clickedColumn = gridPane.getColumnIndex(node);
                int startRow = gridPane.getRowIndex(circles.get(circles.size()-1));
                int startColumn = gridPane.getColumnIndex(circles.get(circles.size()-1));
                if ((Math.abs(clickedRow - startRow) == 1 && clickedColumn == startColumn) ||
                        (Math.abs(clickedColumn - startColumn) == 1 && clickedRow == startRow)) {
                    circles.add((Circle) node);
                }
            }
            drawLines();
        }
    }

    @FXML
    private void onMouseReleased() {
        if(lines.size()>0) {
            gridPane.getChildren().removeAll(lines);
            lines.clear();
            gridPane.getChildren().removeAll(circles);
            Score();
            for (Circle circle : circles) {
                field[gridPane.getRowIndex(circle)][gridPane.getColumnIndex(circle)]=0;}
            addCircle();
            Lose();
        }
        else{circles.clear();}

    }



    private void drawLines() {
        for (int i = 0; i < circles.size()-1; i++) {
            Circle circle1 = circles.get(i);
            Circle circle2 = circles.get(i+1);
            int col1 = GridPane.getColumnIndex(circle1);
            int row1 = GridPane.getRowIndex(circle1);
            int col2 = GridPane.getColumnIndex(circle2);
            int row2 = GridPane.getRowIndex(circle2);
            color = circle1.getFill();
            // Создаем линию между двумя ячейками
            Line line = new Line(circle1.getLayoutX(), circle1.getLayoutY(), circle2.getLayoutX(), circle2.getLayoutY());
            line.setStroke(color); // устанавливаем цвет линии
            line.setStrokeWidth(8); // устанавливаем толщину линии
            gridPane.getChildren().add(line); // добавляем линию на GridPane
            int i1,i2,i3,i4;
            if(col2>=col1) {i3=col2 - col1 + 1; i1=col1;} else {i3=col1 - col2 + 1;i1=col2;}
            if (row2>=row1){i4=row2 - row1 + 1;i2=row1;} else {i4=row1-row2+1;i2=row2;}
            GridPane.setConstraints(line, i1, i2, i3, i4, HPos.CENTER, VPos.CENTER);
            lines.add(line);
        }

    }
    @FXML
    private void addCircle() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (field[i][j]==0){
                    field[i][j]=(int) (Math.random() * 4)+1;
                    Circle circle = new Circle(15);
                    gridPane.add(circle, j, i);
                    GridPane.setHalignment(circle, HPos.CENTER);
                    GridPane.setValignment(circle, VPos.CENTER);
                    if (field[i][j] == 3) {
                        circle.setFill(ORANGERED); // устанавливаем красный цвет для круга с значением 3
                    }
                    else if (field[i][j] == 4) {
                        circle.setFill(LIMEGREEN); // устанавливаем зеленый цвет для круга с значением 4
                    }
                    else if (field[i][j] == 2) {
                        circle.setFill(YELLOW); // устанавливаем желтый цвет для круга с значением 2
                    }
                    else if (field[i][j] == 1) {
                        circle.setFill(Color.DODGERBLUE); // устанавливаем синий цвет для круга с значением 1
                    }
                }
            }
        }
        circles.clear();
    }

    private void Score(){
        if(color==LIMEGREEN && green<15){
            green=green+ circles.size();
            if(green>15){green=15;}
            Green.setText(String.valueOf(green));
        }
        if(color==YELLOW && yellow<15){
            yellow=yellow+ circles.size();
            if(yellow>15){yellow=15;}
            Yellow.setText(String.valueOf(yellow));
        }
        if(color==ORANGERED && red<15){
            red=red+ circles.size();
            if(red>15){red=15;}
            Red.setText(String.valueOf(red));
        }
        if(color==DODGERBLUE && blue<15 ){
            blue=blue+ circles.size();
            if(blue>15){blue=15;}
            Blue.setText(String.valueOf(blue));
        }
        moves--;

        if (red==15 && yellow==15 && blue==15 && green==15)
        {
            win.setVisible(true);
            gridPane.setVisible(false);
        }
        if (moves == 0 && (red < 15 || yellow < 15 || blue < 15 || green<15))
        {
                lose.setVisible(true);
                gridPane.setVisible(false);
        }
        Moves.setText(String.valueOf(moves));
    }
    public void Lose() {
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-1; j++) {
                if(field[i][j]==field[i][j+1]){k++;}
            }
        }
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows-1; i++){
                if(field[i][j]==field[i+1][j]){k++;}
            }
        }
        if(k==0){
            lose.setVisible(true);
            gridPane.setVisible(false);
        }

    }


    @FXML
    void initialize(){
        Red.setText(String.valueOf(red));
        Blue.setText(String.valueOf(blue));
        Yellow.setText(String.valueOf(yellow));
        Green.setText(String.valueOf(green));
        Moves.setText(String.valueOf(moves));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (gridPane.getChildren().size() <= i * columns + j) { // проверяем наличие круга в списке дочерних элементов
                    Circle circle = new Circle(15);
                    gridPane.add(circle, j, i);
                    GridPane.setHalignment(circle, HPos.CENTER);
                    GridPane.setValignment(circle, VPos.CENTER);
                }
                Circle circle = (Circle) gridPane.getChildren().get(i * columns + j); // получаем круг по индексу ячейки
                if (field[i][j] ==3) {
                    circle.setFill(ORANGERED); // устанавливаем красный цвет для круга с значением 3
                }
                else if (field[i][j] == 4) {
                    circle.setFill(LIMEGREEN); // устанавливаем желтый цвет для круга с значением 2
                }
                else if (field[i][j] == 2) {
                    circle.setFill(YELLOW); // устанавливаем желтый цвет для круга с значением 2
                }
                else if (field[i][j] == 1){
                    circle.setFill(Color.DODGERBLUE); // устанавливаем синий цвет для круга с значением 1
                }
            }
        }

    }
}
