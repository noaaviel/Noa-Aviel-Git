package View;

import java.util.ArrayList;

import Model.BroadcastCon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View {

	private Button broadcast, ok;
	private Text title, start, duration, author;
	private TextField t, s, d, a, bandName, numOfParticipents;
	private RadioButton rbReportage, rbInterview, rbSong;
	private ToggleGroup tgr;
	private Group root;
	private Rectangle rctStart;
	private BorderPane bp;
	private VBox vb;

	public View(Stage stage) {
		root = new Group();
		bp = new BorderPane();
		vb = new VBox();
		ok = new Button("Ok");

		broadcast = new Button("Broadcat");
		vb.getChildren().addAll(broadcast);
		bp.setTop(broadcast);
		bp.setBottom(vb);
		bp.setCenter(root);

		rctStart = new Rectangle();
		rctStart.setWidth(180);
		rctStart.setHeight(100);
		rctStart.setFill(Color.WHITE);
		rctStart.setStroke(Color.BLACK);
		rctStart.setX(160);
		rctStart.setY(70);
		bp.getChildren().addAll(rctStart);

		title = new Text("Title:");
		start = new Text("Start:");
		duration = new Text("Duration:");
		author = new Text("Author:");

		numOfParticipents = new TextField();
		bandName = new TextField();
		t = new TextField();
		s = new TextField();
		d = new TextField();
		a = new TextField();

		Button ok = new Button("Ok");
		// child, colindex,rowindex
		bp.getChildren().add(title);
		bp.getChildren().add(t);
		bp.getChildren().add(start);
		bp.getChildren().add(s);
		bp.getChildren().add(duration);
		bp.getChildren().add(d);
		bp.getChildren().add(author);
		bp.getChildren().add(a);
		bp.getChildren().add(ok);
		
		
		tgr = new ToggleGroup();

		rbInterview = new RadioButton("Interview");
		rbInterview.setToggleGroup(tgr);
		rbReportage = new RadioButton("Reportage");
		rbReportage.setToggleGroup(tgr);
		rbSong = new RadioButton("Song");
		rbSong.setToggleGroup(tgr);

		vb.setMargin(rbReportage, new Insets(1, 20, 5, 20)); // right,bottom,left,top
		vb.setMargin(rbInterview, new Insets(1, 20, 5, 20));
		vb.setMargin(rbSong, new Insets(1, 20, 5, 20));

		bp.setCenter(root);
		Scene scene = new Scene(bp, 400, 400);

		stage.setScene(scene);
		stage.show();

	}

	public Button getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(Button broadcast) {
		this.broadcast = broadcast;
	}

	public TextField getNumOfParticipents() {
		return numOfParticipents;
	}

	public void setNumOfParticipents(TextField numOfParticipents) {
		this.numOfParticipents = numOfParticipents;
	}

	public Text getTitle() {
		return title;
	}

	public TextField getBandName() {
		return bandName;
	}

	public void setBandName(TextField bandName) {
		this.bandName = bandName;
	}

	public void setTitle(Text title) {
		this.title = title;
	}

	public Text getStart() {
		return start;
	}

	public void setStart(Text start) {
		this.start = start;
	}

	public Text getDuration() {
		return duration;
	}

	public void setDuration(Text duration) {
		this.duration = duration;
	}

	public Text getAuthor() {
		return author;
	}

	public void setAuthor(Text author) {
		this.author = author;
	}

	public TextField getT() {
		return t;
	}

	public void setT(TextField t) {
		this.t = t;
	}

	public TextField getS() {
		return s;
	}

	public void setS(TextField s) {
		this.s = s;
	}

	public TextField getD() {
		return d;
	}

	public void setD(TextField d) {
		this.d = d;
	}

	public TextField getA() {
		return a;
	}

	public void setA(TextField a) {
		this.a = a;
	}

	public RadioButton getRbReportage() {
		return rbReportage;
	}

	public void setRbReportage(RadioButton rbReportage) {
		this.rbReportage = rbReportage;
	}

	public RadioButton getRbInterview() {
		return rbInterview;
	}

	public void setRbInterview(RadioButton rbInterview) {
		this.rbInterview = rbInterview;
	}

	public RadioButton getRbSong() {
		return rbSong;
	}

	public void setRbSong(RadioButton rbSong) {
		this.rbSong = rbSong;
	}

	public ToggleGroup getTgr() {
		return tgr;
	}

	public void setTgr(ToggleGroup tgr) {
		this.tgr = tgr;
	}

	public Group getRoot() {
		return root;
	}

	public void setRoot(Group root) {
		this.root = root;
	}

	public Rectangle getRctStart() {
		return rctStart;
	}

	public void setRctStart(Rectangle rctStart) {
		this.rctStart = rctStart;
	}

	public void setBp(BorderPane bp) {
		this.bp = bp;
	}

	public void setVb(VBox vb) {
		this.vb = vb;
	}

	public BorderPane getBp() {
		return bp;
	}

	public VBox getVb() {
		return vb;
	}

	public Button getOk() {
		return ok;
	}

	public void setOk(Button ok) {
		this.ok = ok;
	}
	
	//EVENTS FOR EACH PART

	public void addBroadcastEvent(EventHandler<ActionEvent> event) {
		broadcast.setOnAction(event);
	}

	public void addEventToReportage(EventHandler<ActionEvent> event) {
		rbReportage.setOnAction(event);
	}

	public void addEventToInterview(EventHandler<ActionEvent> event) {
		rbInterview.setOnAction(event);
	}

	public void addEventToSong(EventHandler<ActionEvent> event) {
		rbSong.setOnAction(event);
	}

	public void addEventHandlerToOk(EventHandler<ActionEvent> event) {
		ok.setOnAction(event);
	}
	
	

}
