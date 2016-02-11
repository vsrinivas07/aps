package com.apceps.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {

	public static Long getId(String seqName)
			throws Exception {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;

		String sql = null;
		String sqlUpdate = null;

		Long id = null;
		try {
			connection = ConnectionUtil.getConnection();

			String dbProductName = connection.getMetaData().getDatabaseProductName();

			if ("Oracle".equalsIgnoreCase(dbProductName)) {
				sql = " SELECT " + seqName + ".NEXTVAL  ID FROM DUAL ";
			} else if ("Microsoft SQL Server".equalsIgnoreCase(dbProductName)) {
				sql = " SELECT NEXT_VAL ID FROM AS_SEQUENCES WHERE SEQ_NAME = ? ";
				sqlUpdate = " UPDATE AS_SEQUENCES SET NEXT_VAL = NEXT_VAL + INCR_VAL  WHERE SEQ_NAME = ? ";
			}

			
			pstmt = connection.prepareStatement(sql);
			if ("Microsoft SQL Server".equalsIgnoreCase(dbProductName)) {
				pstmt.setString(1, seqName);
			}

			rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getLong("ID");

			if ("Microsoft SQL Server".equalsIgnoreCase(dbProductName)) {
				pstmt1 = connection.prepareStatement(sqlUpdate);
				pstmt1.setString(1, seqName);
				pstmt1.executeUpdate();
			}

		} catch (Exception ex) {
			  ex.printStackTrace();
              throw ex;
		} finally {
			ConnectionUtil.closeConnection(rs, pstmt);
			ConnectionUtil.closeConnection(connection, pstmt1);

		}
		return id;

	}

}
