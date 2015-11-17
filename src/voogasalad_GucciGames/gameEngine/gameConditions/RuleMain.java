package voogasalad_GucciGames.gameEngine.gameConditions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public class RuleMain {
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		GamePlayerPerson p0 = new GamePlayerPerson(null, 0);
		GamePlayerPerson p = new GamePlayerPerson(null, 1);
		List<GamePlayerPerson> l = new ArrayList<GamePlayerPerson>();
		l.add(p0);
		l.add(p);
		AllPlayers a = new AllPlayers(l);
		ConditionsFactory f = new ConditionsFactory(a);
		List<Integer> i = new ArrayList<Integer>();
		List<Object> s = new ArrayList<Object>();
		i.add(1);
		s.add(i);
		ConditionsCreated c = new ConditionsCreated();
		c = f.createCondition("PlayerUnitCondition", "player", s, c);
		c.loop();
		System.out.println("test1");

	}

}