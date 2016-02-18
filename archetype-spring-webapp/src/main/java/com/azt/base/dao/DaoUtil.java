/**
 * 
 */
package com.azt.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


/**
 * @author lanker
 * 2015年8月3日下午1:32:57
 */
public class DaoUtil {
	private static Logger logger = LoggerFactory.getLogger(DaoUtil.class);
	public static final int executeWithGenKey(JdbcTemplate jdbcTemplate,
			String sql,
			Object... parameters) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			JDBCUtil.set(conn, ps, parameters);	
			ps.execute();
			rs=ps.getGeneratedKeys();
			if(rs.next()){
				return rs.getInt(1);
			}else{
				logger.debug("can not got generatedKeys \n\nsql="+sql);
				return -1;
			}
		} catch (Exception e) {
			logger.debug("can not got generatedKeys \n\nsql="+sql+"\n e="+e.getMessage());
			return -1;
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.closeStatement(ps);
			JDBCUtil.closeConnection(conn);
		}
	}
	public static final <T> T queryForObject(JdbcTemplate jdbcTemplate,String sql, RowMapper<T> rowMapper, Object... args){
		T c = null;
		try {
			c =  jdbcTemplate.queryForObject(sql, rowMapper,args[0]);
		} catch (Exception e) {
			
		}
		return c;
	}
}
