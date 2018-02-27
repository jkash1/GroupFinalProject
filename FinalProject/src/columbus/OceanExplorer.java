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
	//My initial StartSailing
	//we will need to change the movements after CASE
	/*
	private void startSailing(){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent ke) {
			 switch(ke.getCode()){
			 	case RIGHT:
			 		oceanMap.getPlayer().goEast();
			 		break;
			 	case LEFT:
			 		oceanMap.getPlayer().goWest();
			 		break;
			 	case UP:
			 		oceanMap.getPlayer().goNorth();
			 		break;
			 	case DOWN:
			 		oceanMap.getPlayer().goSouth();
			 		break;
			 	default:
			 		break;
			 }
			 updateShips();
			}
		});
	}
	
	*/
	
	
	
	
	
	/*
	 * Main launches the game
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
