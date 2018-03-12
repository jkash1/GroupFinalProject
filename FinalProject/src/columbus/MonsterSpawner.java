package columbus;

import java.util.Random;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class MonsterSpawner implements Runnable{

	Boolean running = true;
	int radius;
	Random random = new Random();
	int scalingFactor;
	OceanExplorer gameInstance;
	
	//list of all monsters
	Monster[] monsterSprites = new Monster[20];
	
	public MonsterSpawner(int scalingFactor, OceanExplorer instance){
		
		//initializes the concrete monster factories
		MonsterFactory sharkFactory = FactoryChooser.getFactory("Shark");
		MonsterFactory giantSquidFactory = FactoryChooser.getFactory("GiantSquid");
		
		//make Monsters
		for(int j = 0; j < 20; j++){
			int x = random.nextInt(30);
			int y = random.nextInt(30);	
			
			//makes 10 sharks and 10 squids
			if(j < 10)
				monsterSprites[j] = sharkFactory.makeMonster(x, y);
			else 
				monsterSprites[j] = giantSquidFactory.makeMonster(x, y);
		}
		
		this.radius = 10;
		this.scalingFactor = scalingFactor;
		
		gameInstance = instance;
	}
	
	//adds the monsters to the scene
	public void addToPane(ObservableList<Node> sceneGraph){
		for(Monster monsterSprite: monsterSprites){
			ImageView image = monsterSprite.getImage();
			sceneGraph.add(image);
		}
	}
	@Override
	public void run() {		
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(Monster monsterSprite: monsterSprites){
				//move method implements random move method from professor
				monsterSprite.move();
			}

			if(gameInstance.damage < 3) {
				Platform.runLater(new Runnable() {
					public void run() {
						gameInstance.checkDamage();
					}
				});
			}
		}
	}
}
