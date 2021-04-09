package View;

import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.Observable;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.shape.*;

public class View {
	private RadioButton rbOfficeRoom, rbComputerLab, rbClassRoom, rbCafeteria;
	// private CheckBox cbOfficeRoom, cbComputerLab, cbClassRoom , cbCafeteria;
	private CheckBox cbClean, cbBooked;
	private Group root;
	private ToggleGroup tgr;
	private Rectangle[] visualRooms;
	private ComboBox choosingBox;

	public View(Stage stage) {
		// arranging the borders by 5 different areas
		BorderPane bp = new BorderPane();
		root = new Group();
		choosingBox = new ComboBox();
		visualRooms = new Rectangle[30];

		// left side of stage
		VBox vb = new VBox();
		tgr = new ToggleGroup();

		rbOfficeRoom = new RadioButton("Office Room");
		rbOfficeRoom.setToggleGroup(tgr);
		rbComputerLab = new RadioButton("Computer Lab");
		rbComputerLab.setToggleGroup(tgr);
		rbCafeteria = new RadioButton("Cafeteria");
		rbCafeteria.setToggleGroup(tgr);
		rbClassRoom = new RadioButton("Class Room");
		rbClassRoom.setToggleGroup(tgr);

		vb.getChildren().addAll(rbOfficeRoom, rbCafeteria, rbClassRoom, rbComputerLab);
		// של הצורה שוליים פנימיים
		vb.setMargin(rbCafeteria, new Insets(5, 20, 5, 20)); // right,bottom,left,top
		vb.setMargin(rbClassRoom, new Insets(5, 20, 5, 20));
		vb.setMargin(rbComputerLab, new Insets(5, 20, 5, 20));
		vb.setMargin(rbOfficeRoom, new Insets(5, 20, 5, 20));

		bp.setLeft(vb);
		
		VBox vb1 = new VBox();
		cbBooked = new CheckBox("book room");
		cbClean = new CheckBox("clean room");
		vb1.setMargin(cbBooked, new Insets(5, 20, 5, 20));
		vb1.setMargin(cbClean, new Insets(5, 20, 5, 20));
	
		// create comboBox
		choosingBox = new ComboBox<>();
		choosingBox.setPromptText("Number Of Pepole");
		choosingBox.getItems().addAll("One" , "Two" , "Three");
		HBox hb = new HBox();
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().add(choosingBox);
		
		vb1.getChildren().addAll(cbBooked, cbClean);
		bp.setRight(vb1);
		// bp.setTop(l3);
		bp.setBottom(hb);
		bp.setCenter(root);
	
		//drawMutualShape();
		Scene scene = new Scene(bp, 500, 500);

		stage.setScene(scene);
		stage.show();
	}

	public void drawIfSelected() {
		//change to setOnAction
		if (rbClassRoom.isSelected()) {
			Rectangle classRoom = drawMutualShape();
			root.getChildren().add(classRoom);
		}else if(rbComputerLab.isSelected()) {
			Rectangle computerLab = drawMutualShape();
			root.getChildren().add(computerLab);
		}else if (rbCafeteria.isSelected()) {
			Rectangle cafeteria = drawMutualShape();
			root.getChildren().add(cafeteria);
		}else if(rbOfficeRoom.isSelected()) {
			Rectangle officeRoom = drawMutualShape();
			root.getChildren().add(officeRoom);
		}
	}

	public Rectangle drawMutualShape() {
		Rectangle rct = new Rectangle(100, 100, 100, 100);
		rct.setFill(Color.LIGHTBLUE);
		rct.setStroke(Color.BLACK);
		return rct;
	}

	public RadioButton getRbOfficeRoom() {
		return rbOfficeRoom;
	}

	public RadioButton getRbComputerLab() {
		return rbComputerLab;
	}

	public RadioButton getRbClassRoom() {
		return rbClassRoom;
	}

	public RadioButton getRbCafeteria() {
		return rbCafeteria;
	}

	public Group getRoot() {
		return root;
	}

	public ToggleGroup getTgr() {
		return tgr;
	}

	public CheckBox getCbClean() {
		return cbClean;
	}

	public CheckBox getCbBooked() {
		return cbBooked;
	}
	
/*	public void addChangeListener(ChangeListener<String> cl) {
		//gets what in the comboBox options
		//we need to create a method in the controller that 
		//changes what ever is with cl
		choosingBox.valueProperty().addListener(cl);
	}*/

}
