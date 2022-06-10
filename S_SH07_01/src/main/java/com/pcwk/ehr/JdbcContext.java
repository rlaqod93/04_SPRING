package com.pcwk.ehr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	
	private DataSource dataSource;
	
	public JdbcContext() {
		
	}
	
	// dataSource를 DI 하기 위한 setter생성
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 변하지 않는 부분(DB연결, 자원반납)
	public int workWithStatementStrategy(StatementStrategy stmt) throws SQLException{
		int flag = 0;
		// 1.
		Connection connection = null;
		PreparedStatement pstmt = null;		
		try {
			connection = dataSource.getConnection();
			pstmt = stmt.makeStatement(connection);
			flag = pstmt.executeUpdate();
		}catch(SQLException e) {
			throw e;
		}finally {
			//5.
			// pstmt 자원반납
			if(pstmt != null) {
				try {
					pstmt.close();	
				}catch(SQLException e) {
					
				}
			}
			// connection 자원반납
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException e) {
					
				}
			}
		}
		return flag;
	}
}
