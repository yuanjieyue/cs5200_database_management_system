package gameholic.model;

import java.util.HashMap;
import java.util.Map;

public class Languages {
	private Map<String, Boolean> languageMap;

	public Languages(boolean english, boolean chinese, boolean spanish, boolean french, boolean italian, boolean german) {
		super();
		this.languageMap = new HashMap<>();
		languageMap.put("English", english);
		languageMap.put("Chinese", chinese);
		languageMap.put("Spanish", spanish);
		languageMap.put("French", french);
		languageMap.put("Italian", italian);
		languageMap.put("German", german);
	}

	public Map<String, Boolean> getLanguageMap() {
		return languageMap;
	}

	public void setLanguageMap(Map<String, Boolean> languageMap) {
		this.languageMap = languageMap;
	}
	
}
