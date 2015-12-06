package voogasalad_GucciGames.gameplayer.scenes.concrete;

import java.util.HashMap;
import java.util.Map;
import voogasalad_GucciGames.gameData.GameDataException;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;

public class LoadGameScene extends GameMenuScene {

	public LoadGameScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}

    @Override
    protected Map<String, MenuAction> buildOptionMap () {
        Map<String, MenuAction> optionMap = new HashMap<>();
        for(String s : myManager.getLoader().getAvailableSaves(myManager.getController().getGame().getGameName())){
            optionMap.put(s, () -> selectGame(s));
        }
    return optionMap;
    }
    
    private void selectGame(String gameName){
        myManager.getLoader().loadGameSave(gameName);
        myManager.sceneFinished();
    }
}