package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class AttackCharacteristic extends AMapObjectCharacteristic{
	private double myRange;
	private double myDamage;
	
	public AttackCharacteristic(double range, double damage){
		myRange = range;
		myDamage = damage;
	}
	
	public double getMyRange() {
		return myRange;
	}
	public void setMyRange(double myRange) {
		this.myRange = myRange;
	}
	public double getMyDamage() {
		return myDamage;
	}
	public void setMyDamage(double myDamage) {
		this.myDamage = myDamage;
	}
	
	
}