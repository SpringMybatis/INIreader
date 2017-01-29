package com.itwookie.inireader;

import java.util.regex.Pattern;

public class INIUtils {
	
	/** checks if the grp.key value in cfg is one of the Allowed values. If not, the value will be set to default
	 * @return if grp.key value matched one of allowed
	 */
	public static boolean checkValue (INIConfig cfg, String grp, String key, String Default, String... Allowed){
		String cfgVal = cfg.get(grp, key);
		boolean ret = false;
		for (int i = 0; i < Allowed.length; i++) {
			if (Allowed[i].equals(cfgVal)) {
				ret = true;
				break;
			}
		}
		if (!ret) cfg.set(grp, key, Default);
		return ret;
	}
	
	/** checks if the grp.key value in cfg matches allowed using regex. If not, the value will be set to default
	 * @param Default has to support toString()
	 * @return if grp.key value matched one of allowed
	 */
	public static boolean checkValue (INIConfig cfg, String grp, String key, String Default, Pattern Allowed){
		String cfgVal = cfg.get(grp, key);
		boolean ret = false;
		if (cfgVal != null && (Allowed.matcher(cfgVal).matches())) ret = true;
		if (!ret) cfg.set(grp, key, Default);
		return ret;
	}
	
	/** checks if the grp.key value is integer and in range. If not, the value will be set to default
	 * @param Default has to support toString()
	 * @param Min Included Valid - set to null to ignore lower bound
	 * @param Max Included Valid - set to null to ignore upper bound
	 * @return if grp.key value matched one of allowed
	 */
	public static boolean checkValue (INIConfig cfg, String grp, String key, int Default, Integer Min, Integer Max){
		if (Min != null && Max != null && Min > Max) { Integer tmp = Max; Max = Min; Min = tmp; }
		String cfgVal = cfg.get(grp, key);
		boolean ret = false;
		Integer value = null;
		try { 
			if (cfgVal != null) {
				value = Integer.valueOf(cfgVal);
			}
		} catch (Exception e) { }
		if (value != null) {
			ret = true;
			if (Min != null && value < Min) ret = false;
			if (Max != null && value > Max) ret = false;
		}
		if (!ret) cfg.set(grp, key, String.valueOf(Default));
		return ret;
	}
}
