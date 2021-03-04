package com.capgemini.mrchecker.database;

import com.capgemini.mrchecker.database.core.BasePageDatabase;
import com.capgemini.mrchecker.database.core.DriverManager;

public class ParametrizedDatabase extends BasePageDatabase {
	
	@Override
	public String getDatabaseUnitName() {
		return DriverManager.PARAMETRIZED_PERSISTENCE_UNIT;
	}
}
