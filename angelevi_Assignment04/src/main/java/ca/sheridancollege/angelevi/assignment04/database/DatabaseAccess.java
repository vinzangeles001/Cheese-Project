package ca.sheridancollege.angelevi.assignment04.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.angelevi.assignment04.beans.Cheese;
import ca.sheridancollege.angelevi.assignment04.beans.Units;
import ca.sheridancollege.angelevi.assignment04.beans.User;

@Repository
public class DatabaseAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	//this method is for getting the Units of measure 
	//for the cheese
	public List<Units> getUnits(){
		String sql = "SELECT * FROM units ORDER BY description;";
		List<Units> units = jdbc.query(sql, new BeanPropertyRowMapper
				<Units>(Units.class));
		return units;
	}
	
	//getUnitsMap is for displaying the units where I list it.
	public Map<String, Units> getUnitsMap(){
		List<Units> units = getUnits();
		Map<String, Units> unitMap = new HashMap<String, Units>();
		for(Units t: units) {
			unitMap.put(t.getDescription(), t);
		}
		return unitMap;
	}
	
	// this method is for showing the value inside the Cheeses database
	public List<Cheese> getCheeseInventory(){
		String sql = "SELECT * FROM cheeses ORDER BY id;";
		List<Cheese> cheese = jdbc.query(sql, new BeanPropertyRowMapper
				<Cheese>(Cheese.class));
		return cheese;
	}
	//this insertCheese method is for inserting some values 
	//inside the database
	public int insertCheese(Cheese cheese) {
		String sql = "INSERT INTO cheeses( name, quantity, weight, unitsId, price, specSheet)"
				+ "VALUES(:name, :qty, :weight, :units, :price, :spec);";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", cheese.getName())
			.addValue("qty", cheese.getQuantity())
			.addValue("weight", cheese.getWeight())
			.addValue("units", cheese.getUnitsId())
			.addValue("price", cheese.getPrice())
			.addValue("spec", cheese.getSpecSheet());
		
		return jdbc.update(sql, params);
	}
	
	//this method is for showing the distinct table of the database
	public List<Cheese> getCheeses(){
		String sql = "SELECT DISTINCT name, unitsId, weight FROM cheeses;";
		List<Cheese> cheese = jdbc.query(sql, new BeanPropertyRowMapper<Cheese>(Cheese.class));
		return cheese;
	}
	
	//this method is for finding the user value inside the database.
	public User findUser(String userName) {
		String sql = "SELECT * FROM users WHERE userName=:userName;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userName", userName);
		
		List<User> list = jdbc.query(sql, params, new BeanPropertyRowMapper<User>(User.class));
		
		if(list.size() > 0) {
			return list.get(0);
		}
		else {
			return null;
		}
		
	}
	
	//this method is for getting the roles by using the userId 
	public List<String> getRolesByUserId(long userId){
		String sql = "SELECT user_role.userId, roles.roleName FROM user_role, roles "
				+ "WHERE user_role.roleId=roles.roleId AND user_role.userId=:user;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("user", userId);
		
		List<String> roles = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbc.queryForList(sql, params);
		
		for (Map<String, Object> row : rows) {
			roles.add((String)row.get("rolename"));
		}
		return roles;

	}
	
	//this method is for inserting values inside the database. 
	public long addUser(String userName, String password, String email) {
		String sql="INSERT INTO users (userName, email, password, enabled) "
				+ "VALUES (:userName, :email, :password, :true);";
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("userName", userName)
			.addValue("email", email)
			.addValue("password", encoder.encode(password))
			.addValue("true", true);
			
		return jdbc.update(sql, params);
	}
	
	//this method is for adding a role in the user. putting value in 
	//your user will give the user a role. 
	public int AddUserRole(long userId, long roleId) {
		String sql = "INSERT INTO user_role (userId, roleId) VALUES(:user, :role);";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("user", userId)
			.addValue("role", roleId);
		return jdbc.update(sql, params);
	}
	
	
	
}
