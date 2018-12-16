package gameholic.model;

import java.util.HashMap;
import java.util.Map;

public class Genres {
	private Map<String, Boolean> genreMap;



	public Genres(boolean action, boolean strategy, boolean sports, boolean simulation, boolean racing, boolean rolePlaying, boolean adventure,
			boolean fighting, boolean shooter, boolean casual, boolean musicParty) {
		super();
		this.genreMap = new HashMap<>();
		genreMap.put("Action", action);
		genreMap.put("Strategy", strategy);
		genreMap.put("Sports", sports);
		genreMap.put("Simulation", simulation);
		genreMap.put("Racing", racing);
		genreMap.put("Role Playing", rolePlaying);
		genreMap.put("Adventure", adventure);
		genreMap.put("Fighting", fighting);
		genreMap.put("Shooter", shooter);
		genreMap.put("Casual", casual);
		genreMap.put("Music Party", musicParty);
		
		
	}

	public Map<String, Boolean> getGenreMap() {
		return genreMap;
	}

	public void setGenreMap(Map<String, Boolean> genreMap) {
		this.genreMap = genreMap;
	}
	
	

}
