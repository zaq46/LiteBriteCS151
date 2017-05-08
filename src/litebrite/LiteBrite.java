/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package litebrite;

import javax.xml.stream.events.StartDocument;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author narayan
 */
public class LiteBrite extends Application {

	@Override
	public void start(final Stage stage) throws Exception {
		int rows = 50;
		int columns = 50;

		stage.setTitle("Enjoy your game");

		GridPane grid = new GridPane();
		grid.getStyleClass().add("game-grid");

		final ColorPicker colorPicker = new ColorPicker();
		
		for (int i = 0; i < columns; i++) {
			ColumnConstraints column = new ColumnConstraints(10);
			grid.getColumnConstraints().add(column);
		}

		for (int i = 0; i < rows; i++) {
			RowConstraints row = new RowConstraints(10);
			grid.getRowConstraints().add(row);
		}

		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				Pane pane = new Pane();
				
				pane.setOnMouseReleased(e -> {
					if(pane.getChildren().isEmpty())
						pane.getChildren().add(Anims.getAtoms(colorPicker));
					else{
						pane.getChildren().clear();
					}
				});
				pane.getStyleClass().add("game-grid-cell");
				if (i == 0) {
					pane.getStyleClass().add("first-column");
				}
				if (j == 0) {
					pane.getStyleClass().add("first-row");
				}
				grid.add(pane, i, j);
			}
		}
		colorPicker.setValue(Color.CORAL);
		VBox box = new VBox();

		box.getChildren().addAll(colorPicker, grid);
		
		
		Scene scene = new Scene(box, (columns * 10) + 20, (rows * 10) + 50);
		scene.getStylesheets().add(LiteBrite.class.getResource("resources/game.css").toExternalForm());
		stage.setScene(scene);
		stage.show();

	}

	public static class Anims {

		public static Node getAtoms(final ColorPicker colorPicker) {
			Circle c = new Circle(5);
			c.setFill(colorPicker.getValue());
			c.setCenterX(5);
			c.setCenterY(5);
			return c;
		}
	}

	public static void main(final String[] arguments) {
		Application.launch(arguments);
	}

}
