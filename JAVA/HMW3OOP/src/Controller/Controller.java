package Controller;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.shape.Rectangle;

public class Controller {

	// giving access to model and view in Controller
	private Model.Model theModel;
	private View.View theView;
	
	public Controller( View.View view,Model.Model model) {
		theView = view;
		theModel = model;

	}

}
