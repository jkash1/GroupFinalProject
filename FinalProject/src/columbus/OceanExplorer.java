package columbus;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OceanExplorer extends Application{

	public final int scale = 40;
	final int dimensions = 40;
	OceanMap oceanMap;
	Scene scene;
	AnchorPane root;
	
	
	
	
	@Override
	public void start(Stage oceanStage) throws Exception {
		scene = new Scene(root, 800, 600);
		oceanStage.setScene(scene);
		oceanStage.setTitle("Columbus vs. the Deep Blue");
		
		oceanStage.show();
		
	//	startSailing();
		
	}

	
	/*
	 * Main launches the game
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
