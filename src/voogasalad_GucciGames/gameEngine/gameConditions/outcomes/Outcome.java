package voogasalad_GucciGames.gameEngine.gameConditions.outcomes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Outcome {
	private List<Conditions> myConditions = new ArrayList<Conditions>();

	public Outcome(List<Conditions> conditions, Map<String, Object> conditionParams) {
		myConditions = conditions;
	}

	abstract BasicParameters applyOutcome(BasicParameters params, int i);

	public void addCondition(Conditions condition) {
		myConditions.add(condition);
	}

	public final BasicParameters executeOutcome(BasicParameters params) {
		List<Integer> players = params.getEngine().getPlayers().getAllIds();
		for (int i = 0; i < players.size(); i++) {
			GamePlayerPerson cur = params.getEngine().getPlayers().getPlayerById(players.get(i));
			if (checkConditions(params,cur)) {
				params = applyOutcome(params, cur.getMyPlayerId());
			}
		}
		return params;// might need to change the return type
	}

	private Boolean checkConditions(BasicParameters params, GamePlayerPerson player) {

		if (myConditions.isEmpty() || myConditions == null) {
			return true;
		} else {
			Boolean flag = true;
			Iterator<Conditions> itr = myConditions.iterator();
			while (itr.hasNext() && flag == true) {
				flag = itr.next().execute(params,player);
			}
			return flag;
		}

	}
}