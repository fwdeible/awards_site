package com.project.awardParser.swingUI;

import java.util.ArrayList;

import com.project.awardParser.model.Award;


public class AwardFactory {

	private static ArrayList<Award> awardList;

	/**
	 * gets the full list of awards
	 * 
	 * @return the full list of awards
	 */
	public static ArrayList<Award> getAwards() {
		return getAwards(false);
	}

	/**
	 * gets the full list of awards
	 * 
	 * @param useDefaults
	 *            - true for testing purposes only
	 * @return
	 */
	public static ArrayList<Award> getAwards(boolean useDefaults) {
		if (null == awardList || awardList.isEmpty()) {
			initAwards(useDefaults);
		}
		return awardList;
	}

	private static void initAwards(boolean useDefaults) {

		AwardFactory.awardList = new ArrayList<Award>();

		// TODO: Determine data accessor type dynamically

		AwardDataAccessorLocalImpl accessor = null;

		// try to retrieve list from db
//		try {
//			accessor = new AwardDataAccessorSQLImpl();
//		} catch (Exception e) {
//			System.err.println("Failed to load db accessor " + e.getMessage());
//		}
//
//		if (null == accessor && useDefaults) {
			accessor = new AwardDataAccessorLocalImpl();

//		}

		awardList.addAll(accessor.getAllAwards());

	}

}
