
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Model.Model model = new Model.Model();
		
		View.View view = new View.View(stage);
		
		Controller.Controller controller = new Controller.Controller(view,model);

	}

}
