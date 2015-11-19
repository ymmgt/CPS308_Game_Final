package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.HashSet;
import java.util.Set;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.MovableCharacteristic;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRules.defaultRules.UnitsMovablePerTurn;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

// Test at testing.TestActions.java

public class WhereToMoveEvent extends MapObjectEvent{

	public WhereToMoveEvent(String actionName) {
		super(actionName);
		getRuleList().add(new UnitsMovablePerTurn());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CommunicationParameters execute(CommunicationParameters params) {
		// TODO Auto-generated method stub
		BasicParameters basic = (BasicParameters) params;
		AllPlayers players = basic.getPlayers();
		TargetCoordinateMultiple result = new TargetCoordinateMultiple();
		MapObject calledMe = basic.getCalledMe();

		// getting the range
		MovableCharacteristic mc = (MovableCharacteristic) calledMe.getCharacteristic("MovableCharacteristic");
		double range = mc.getRange();
		// going through neutral player
		TargetCoordinateSingle caller = (TargetCoordinateSingle) calledMe.getCoordinate();
		Set<TargetCoordinateSingle> otherCoor = new HashSet<>();
		
		players.getNonNeutralMapObjects().stream().forEach(obj -> {
			otherCoor.add((TargetCoordinateSingle) obj.getCoordinate());
		});
		players.getActivePlayer(-1).getMapObjects().stream().forEach(mo -> {
			if(mo.getObjectType().isTile()){
				TargetCoordinateSingle single = (TargetCoordinateSingle) mo.getCoordinate();
				double dx = Math.abs(single.getCenterX()-caller.getCenterX());
				double dy = Math.abs(single.getCenterY()-caller.getCenterY());

				if (checkNeighborhood(dx,dy,range) && !otherCoor.contains(mo.getCoordinate())){
					result.addTargetCoordinateSingle(mo.getCoordinate());
				}
			}
		});
		return new GridCoordinateParameters(result);
	}


}
