
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Run extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Model.Model model = new Model.Model();
		View.View view = new View.View(primaryStage);
		Controller.Controller controller = new Controller.Controller(model, view);
		
	}

}
