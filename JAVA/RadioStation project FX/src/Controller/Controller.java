package Controller;

import Model.Interview;
import Model.InvalidInput;
import Model.Reportage;
import Model.Song;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Controller {

	// giving access to model and view in Controller
	private Model.Model theModel;
	private View.View theView;

	public Controller(Model.Model model, View.View view) {
		theModel = model;
		theView = view;

		theView.addBroadcastEvent(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// rbReportage, rbInterview, rbSong
				theView.getVb().getChildren().clear();
				theView.getVb().getChildren().addAll(theView.getRbReportage(), theView.getRbInterview(),
						theView.getRbSong());

			}
		});

		view.addEventToInterview(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				theView.getVb().getChildren().clear();
				theView.getVb().setStyle("-fx-background-color:RED; -fx-opacity:1;");
				Text np = new Text("Number Of Participants");
				Text au = new Text("Author Name");
				theView.getVb().getChildren().addAll(theView.getOk(), au, theView.getA(), np,
						theView.getNumOfParticipents(), theView.getTitle(), theView.getT(), theView.getDuration(),
						theView.getD(), theView.getStart(), theView.getS());

			}

		});

		view.addEventToReportage(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				theView.getVb().getChildren().clear();
				theView.getVb().setStyle("-fx-background-color:BLUE; -fx-opacity:1;");

				Text a = new Text("Author Name");
				theView.getVb().getChildren().addAll(theView.getOk(), a, theView.getA(), theView.getTitle(),
						theView.getT(), theView.getDuration(), theView.getD(), theView.getStart(), theView.getS());

			}

		});

		view.addEventToSong(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				theView.getVb().getChildren().clear();
				theView.getVb().setStyle("-fx-background-color:GREEN; -fx-opacity:1;");

				Text bn = new Text("Band Name");
				theView.getVb().getChildren().addAll(theView.getOk(), bn, theView.getBandName(), theView.getTitle(),
						theView.getT(), theView.getDuration(), theView.getD(), theView.getStart(), theView.getS());
			}

		});

		theView.addEventHandlerToOk(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (theView.getRbReportage().isSelected()) {
					Reportage r = new Reportage();
					Rectangle recr = new Rectangle();
					try {
						r = new Reportage(theView.getT().getText(), Integer.parseInt(theView.getS().getText()),
								Integer.parseInt(theView.getD().getText()), theView.getA().getText());
						System.out.println(r.toString());
						theModel.getAllBroadCasts().add(r);
						recr = new Rectangle(0, 0, Integer.parseInt(theView.getD().getText()) * 3, 100);
						recr.setStroke(Color.BLACK);
						recr.setFill(Color.BLUE);
						recr.setX(160 + Integer.parseInt(theView.getS().getText()) * 3);
						recr.setY(70);
						theModel.getAllRectangles().add(recr);
						theView.getBp().getChildren().add(recr);
					} catch (InvalidInput e) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Exception");
						alert.setHeaderText("Broadcast either got duratuion not in the range of 0-60 "
								+ "or duration is too long for start minute! ");
							alert.showAndWait();
							theModel.getAllBroadCasts().remove(r);
							theModel.getAllRectangles().remove(recr);
							theView.getBp().getChildren().remove(recr);
							
					}
				
					
					
				} else if (theView.getRbSong().isSelected()) {
					Song s = new Song();
					Rectangle recs = new Rectangle();
					try {
						s = new Song(theView.getT().getText(), Integer.parseInt(theView.getS().getText()),
								Integer.parseInt(theView.getD().getText()), theView.getBandName().getText());
						System.out.println(s.toString());
						theModel.getAllBroadCasts().add(s);
						recs = new Rectangle(0, 0, Integer.parseInt(theView.getD().getText()) * 3, 100);
						recs.setStroke(Color.BLACK);
						recs.setFill(Color.GREEN);
						recs.setX(160 + Integer.parseInt(theView.getS().getText()) * 3);
						recs.setY(70);
						theModel.getAllRectangles().add(recs);
						theView.getBp().getChildren().add(recs);
					} catch (InvalidInput e) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Exception");
						alert.setHeaderText("Broadcast either got duratuion not in the range of 0-60 "
								+ "or duration is too long for start minute! ");
							alert.showAndWait();
							theModel.getAllBroadCasts().remove(s);
							theModel.getAllRectangles().remove(recs);
							theView.getBp().getChildren().remove(recs);
					}
					
				} else if (theView.getRbInterview().isSelected()) {
					Interview i = new Interview();
					Rectangle reci = new Rectangle();
					try {
						i = new Interview(theView.getT().getText(), Integer.parseInt(theView.getS().getText()),
								Integer.parseInt(theView.getD().getText()), theView.getA().getText(),
								Integer.parseInt(theView.getNumOfParticipents().getText()));
						System.out.println(i.toString());
						theModel.getAllBroadCasts().add(i);
						reci = new Rectangle(0, 0, Integer.parseInt(theView.getD().getText()) * 3, 100);
						reci.setStroke(Color.BLACK);
						reci.setFill(Color.RED);
						reci.setX(160 + Integer.parseInt(theView.getS().getText()) * 3);
						reci.setY(70);
						theModel.getAllRectangles().add(reci);
						theView.getBp().getChildren().add(reci);
					} catch (InvalidInput e) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Exception");
						alert.setHeaderText("Broadcast either got duratuion not in the range of 0-60 "
								+ "or duration is too long for start minute! ");
					
							alert.showAndWait();
							theModel.getAllBroadCasts().remove(i);
							theModel.getAllRectangles().remove(reci);
							theView.getBp().getChildren().remove(reci);
					}
					
				}
				theView.getVb().getChildren().clear();
				theView.getVb().setStyle("-fx-background-color:#fff1; -fx-opacity:1;");
			}

		});

	}

}
