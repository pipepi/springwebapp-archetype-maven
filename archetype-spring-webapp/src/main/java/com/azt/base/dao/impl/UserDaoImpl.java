package com.azt.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.azt.base.dao.DaoUtil;
import com.azt.base.dao.UserDao;
import com.azt.base.model.User;
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> HANDLE = new RowMapper<User>() {
		public User mapRow(ResultSet row, int index) throws SQLException {
			User u = new User();
			u.id = row.getInt("id");
			u.name = row.getString("name");
			u.nickName = row.getString("nick_name");
			u.pwd = row.getString("pwd");
			return u;
		}
	};
	public int add(User user) {
		StringBuffer sql = new StringBuffer("insert into t_user(");
		sql.append("name,")
		.append("nick_name,")
		.append("pwd,")
		.append("creat_time,")
		.append("update_time)")
		.append(" values (?,?,?,?,?)");
		
		Date now = new Date();
		return DaoUtil.executeWithGenKey(jdbcTemplate, sql.toString(), 
				user.name,
				user.nickName,
				user.pwd,
				now,
				now
				);
	}
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}
	public User query(int id) {
		// TODO Auto-generated method stub
		return null;
	}    
	
}
