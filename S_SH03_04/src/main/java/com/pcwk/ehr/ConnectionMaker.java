package com.pcwk.ehr;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	
	/**
	 * 약한 결함을 위한 interface 도입
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection makeConection()throws ClassNotFoundException,SQLException;
}
