package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

public class RuleParams {
	
	private String name;
	
	private String displayName;
	
	public RuleParams(String name){
		this.name = name;
		
	}
	
	public void setDisplayName(String n){
		this.displayName = n;
	}
	
	public String getDisplayName(){
		return this.displayName;
	}

}