package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.TileCharacteristic;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;

public class MapObjectType{


	private String myName;
	private String myImagePath;
//	private CharacteristicHandler myCharacteristicHandler;
	private ResourceBundle myResourceBundle;

	//sizes

	private Map<String, MapObjectEvent> myActions; //test
	private Map<String, MapObjectEvent> myRequests;
	//sizes
	


	
	public MapObjectType(String name, String imagePath){
		myName = name;
		myImagePath = imagePath;

		// TreeMap so alphabetized when giving to front end
		
		myActions = new TreeMap<>();
		myRequests = new TreeMap<>();

	}

	//public void changeHealth(double healthDiff){
	//	myHealthCharacteristic.changeHealth(healthDiff);
	//}

	public String getName() {
		return myName;
	}

	public String getImagePath() {
		return myImagePath;
	}

	@Override
	public String toString(){
		return myName;
	}

	public MapObjectEvent getAction(String name){
		return myActions.get(name);
	}


	public void addAction(String name, MapObjectEvent action){
		this.myActions.put(name, action);
	}

	public List<String> getActionStrings(){
		return new ArrayList<String>(this.myActions.keySet());
	}
		public boolean hasAction(String name){
		return this.myActions.containsKey(name);
	}

	public void addRequest(String name, MapObjectEvent func){
		this.myRequests.put(name, func);
	}

	public MapObjectEvent getRequest(String name){
		return this.myRequests.get(name);
	}

	public boolean hasRequest(String name){
		return this.myRequests.containsKey(name);
	}

	public Map<String, String> getAttributes() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hello", "hi"); //change this later
		return map;
	}
	
}
