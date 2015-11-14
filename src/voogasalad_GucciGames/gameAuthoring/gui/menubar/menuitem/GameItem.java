package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.GameSettingDialog;

class GameItem extends MenuItem{
	GameItem(String name, IGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+M"));
		setOnAction(e -> {
			GameSettingDialog  gameSettingDialog = new GameSettingDialog();
			gameSettingDialog.showGameSettingsDialog();
			//controller.saveToXML();
			//TODO
		});
	}
}
