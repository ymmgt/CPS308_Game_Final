package voogasalad_GucciGames.gameEngine.mapObject;

import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;

public class MapObject {
    private MapObjectType myObjectType;
    private ATargetCoordinate myCoordinate;
    private int ownerID;

    public MapObject(MapObjectType type, ATargetCoordinate coor, int ownerID){
    	this.myObjectType = type;
    	this.myCoordinate = coor;
    }
    
    public MapObjectType getObjectType(){
    	return myObjectType;
    }
    
    public ATargetCoordinate getCoordinate(){
    	return myCoordinate;
    }
    
    public void setCoordinate(ATargetCoordinate coordinate){
    	this.myCoordinate = coordinate;
    }

	public boolean isUnit() {
		return myObjectType.hasCharacteristic("unit");
	}

}
