package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

public class UnitParams extends AParamsObject {
	private String unitName;
	private int numberOfAttacks;
	private int damage;
	private int health;
	private int rangeMvt;
	private int rangeAttack;
	
	public UnitParams(String unitName, int numberOfAttacks, int damage, int health, int rangeMvt, int rangeAttack){
		this.setName(unitName);
		this.numberOfAttacks = numberOfAttacks;
		this.damage = damage;
		this.health = health;
		this.rangeMvt = rangeMvt;
		this.rangeAttack = rangeAttack;
	}

	public int getNumberOfAttacks() {
		return numberOfAttacks;
	}

	public void setNumberOfAttacks(int numberOfAttacks) {
		this.numberOfAttacks = numberOfAttacks;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getRangeMvt() {
		return rangeMvt;
	}

	public void setRangeMvt(int rangeMvt) {
		this.rangeMvt = rangeMvt;
	}

	public int getRangeAttack() {
		return rangeAttack;
	}

	public void setRangeAttack(int rangeAttack) {
		this.rangeAttack = rangeAttack;
	}

	public String getName() {
		return unitName;
	}

	public void setName(String unitName) {
		this.unitName = unitName;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
