package hgm_backend.src.fileHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Iterator;

import org.json.JSONObject;

public class saveConfig {
	public static final String arr = "array";
	public static final String num = "int";
	public static final String str = "string";
	public static final String boo = "boolean";

	public static boolean save() {
		try {
			Writer output = null;
			output = new BufferedWriter(new FileWriter(new File("./_saveMe/config.json")));
			output.write(loadConfig.configOpject.toString());
			output.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Config set
	 * @param name name of the config to change
	 * @param value if null, it's boolean switch. This is to set it to desired config.
	 * @param type possible: arr, num, str, boo
	 */
	public static void setConfig(String name, String value, String type){
		Iterator<String> keys = loadConfig.configOpject.keys();
		while(keys.hasNext()) {
			String key = keys.next();
			if (key.equals(name)) {
				if (type == arr) {} // TODO finish array
				else if (type == num) {loadConfig.configOpject.put(name, Integer.parseInt(value));}
				else if (type == str) {loadConfig.configOpject.put(name, value);}
				else if (type == boo) {loadConfig.configOpject.put(name, !loadConfig.configOpject.getBoolean(name) );}
			}
			if(loadConfig.configOpject.get(key) instanceof JSONObject) {
				JSONObject object = (JSONObject) loadConfig.configOpject.get(key);
				Iterator<String> innerKeys = object.keys();
				while(innerKeys.hasNext()) {
					String innerKey = innerKeys.next();
					if (innerKey.equals(name)) {
						if (type == arr) {} // TODO finish array
						else if (type == num) {object.put(name, Integer.parseInt(value));}
						else if (type == str) {object.put(name, value);}
						else if (type == boo) {object.put(name, !loadConfig.configOpject.getJSONObject(key).getBoolean(name) );}
					}
				}
			}
		}
		save();
	}
}
