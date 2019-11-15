/**
 *  This class saves acts like a holder class for all the elements needed to save a game.
 *  It is craeted with all of the elements needed, and they can be taken out at any time.
 */
package mainGame;

import java.awt.Color;

public class GameSave {
	//VARIABLES
	private String name;
	private int score;
	private double health;
	private int level;
	private int enemy;
	private int levelsRemaining;
	private String ability;
	private int abilityUses;
	private Handler _handler;
	
	
	//CONSTRUCTOR
	public GameSave(String n, int sc, double hp, int lvl, int en, int lvlRem, String ab, int abilUses, Handler handler){
		name = n;
		score = sc;
		health = hp;
		level = lvl;
		enemy = en;
		levelsRemaining = lvlRem;
		ability = ab;
		abilityUses = abilUses;	
		_handler = handler;
	}
	
	public void tick(){
		_handler.clearCoins();
	}
	
	//GETTERS
	public String getName(){
		return name;
	}
	
	public int getScore(){
		return score;
	}
	
	public double getHealth(){
		return health;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getEnemy(){
		return enemy;
	}
	
	public int getLevelsRem(){
		return levelsRemaining;
	}
	
	public String getAbility(){
		return ability;
	}
	
	public int getAbilityUses(){
		return abilityUses;
	}
	//SETTERS
}
