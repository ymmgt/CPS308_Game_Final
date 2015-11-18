package voogasalad_GucciGames.gameEngine;

import java.util.HashMap;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

public class Main {

	public static void main(String[] args) {
		GamePlayerPerson p0 = new GamePlayerPerson();
		GamePlayerPerson p = new GamePlayerPerson();
		Map<Integer, GamePlayerPerson> map = new HashMap<Integer,GamePlayerPerson>();
		map.put(-1, p);
		map.put(0, p0);
		AllPlayers allPlayers = new AllPlayers(map);
		MainGameEngine engine = new MainGameEngine(allPlayers);
		engine.endTurn();

	}
}

/*
 * public class Main {
 *
 * public static void main(String[] args){
 *
 *
 * List<GamePlayerPerson> myListOfPlayers = new ArrayList<GamePlayerPerson>();
 *
 *
 * UnitCollection neutralUnits = new UnitCollection(); myListOfPlayers.add(new
 * GamePlayerPerson(neutralUnits, 0)); //neutral player
 *
 * UnitCollection p1Units = new UnitCollection();
 *
 * GameUnitType soldier = new GameUnitType("soldier", null); GameUnitType archer
 * = new GameUnitType("archer" , null);
 *
 * p1Units.addUnit(new GameUnit(1, soldier, 3, 3)); p1Units.addUnit(new
 * GameUnit(1, archer, 1, 1)); myListOfPlayers.add(new GamePlayerPerson(p1Units,
 * 0)); //player 1
 *
 * UnitCollection p2Units = new UnitCollection();
 *
 * p2Units.addUnit(new GameUnit(2, soldier, 2, 2));
 *
 * myListOfPlayers.add(new GamePlayerPerson(p2Units, 0)); //player 2
 *
 *
 * AllPlayers myPlayers = new AllPlayers(myListOfPlayers);
 *
 *
 * GameMap myMap = new GameMap(myPlayers); NoUnitsGlobalGameRule myRule = new
 * NoUnitsGlobalGameRule(myMap); MainGameEngine myEngine = new
 * MainGameEngine(myPlayers, myRule, myMap);
 *
 *
 * while(myRule.gameEnded().size() == 0){
 *
 * myEngine.takeTurn();
 *
 * }
 *
 * } }
 */
// initialize Controller
