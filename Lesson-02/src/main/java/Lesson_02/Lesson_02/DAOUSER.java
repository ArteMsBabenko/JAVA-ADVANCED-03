package Lesson_02.Lesson_02;


import Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Domain.User;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class DAOUSER {
	
	private Logger log = Logger.getLogger(DAOUSER.class);

	public User insert(String firstName, String lastName, String email, String password, String accessLevel)
			throws DAOEXCEPTION {
		log.info("Creating new user in database...");
		String sqlQuery = "insert into user(`first_name`, `last_name`, `email`, `password`, `access_level`) values (?, ?, ?, ?, ?)";

		User user = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection...");
			connection = DAOFACTORY.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, email);
			statement.setString(4, password);
			statement.setString(5, accessLevel);

			log.trace("Executing database update...");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) added!", rows));

			log.trace("Getting result set...");
			if (rows == 0) {
				log.error("Creating user failed, no rows affected!");
				throw new DAOEXCEPTION("Creating user failed, no rows affected!");
			} else {
				resultSet = statement.getGeneratedKeys();

				if (resultSet.next()) {
					log.trace("Creating User to return...");
					user = new User(resultSet.getInt(1), firstName, lastName, email, password, accessLevel);
				}
			}
		} catch (SQLException e) {
			log.error("Creating user failed!");
			throw new DAOEXCEPTION("Creating user failed!", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed!");
			} catch (SQLException e) {
				log.error("Result set can't be closed!", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		log.trace("Returning User...");
		log.info(user + " is added to database!");
		return user;
	}

	public List<User> readAll() throws DAOEXCEPTION {
		log.info("Getting all users from database...");
		String sqlQuery = "select * from user";

		List<User> userList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection...");
			connection = DAOFACTORY.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);

			log.trace("Getting result set...");
			resultSet = statement.executeQuery();

			log.trace("Creating list of users to return...");
			while (resultSet.next()) {
				userList.add(new User(resultSet.getInt("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"),
						resultSet.getString("access_level")));
			}
		} catch (SQLException e) {
			log.error("Getting list of users failed!");
			throw new DAOEXCEPTION("Getting list of users failed!", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed!");
			} catch (SQLException e) {
				log.error("Result set can't be closed!", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		log.trace("Returning list of users...");
		return userList;
	}

	public User readByID(int id) throws DAOEXCEPTION {
		log.info("Getting user by id from database...");
		String sqlQuery = "select * from user where id = ?";

		User user = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection...");
			connection = DAOFACTORY.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, id);

			log.trace("Getting result set...");
			resultSet = statement.executeQuery();

			log.trace("Creating User to return...");
			while (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"),
						resultSet.getString("access_level"));
			}
		} catch (SQLException e) {
			log.error("Getting user by id failed!");
			throw new DAOEXCEPTION("Getting user by id failed!", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed!");
			} catch (SQLException e) {
				log.error("Result set can't be closed!", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		log.trace("Returning User...");
		log.info(user + " is getted from database!");
		return user;
	}

	public User readByEmail(String email) throws DAOEXCEPTION {
		log.info("Getting user by email from database...");
		String sqlQuery = "select * from user where email = ?";

		User user = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection...");
			connection = DAOFACTORY.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, email);

			log.trace("Getting result set...");
			resultSet = statement.executeQuery();

			log.trace("Creating User to return...");
			while (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"),
						resultSet.getString("access_level"));
			}
		} catch (SQLException e) {
			log.error("Getting user by email failed!");
			throw new DAOEXCEPTION("Getting user by email failed!", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed!");
			} catch (SQLException e) {
				log.error("Result set can't be closed!", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		log.trace("Returning User...");
		log.info(user + " is getted from database!");
		return user;
	}

	public boolean updateByID(int id, String firstName, String lastName, String email, String password, String accessLevel)
			throws DAOEXCEPTION {
		log.info("Updating user by id in database...");
		String sqlQuery = "update user set first_name = ?, last_name = ?, email = ?, password = ?, access_level = ? where id = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;

		try {
			log.trace("Opening connection...");
			connection = DAOFACTORY.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, email);
			statement.setString(4, password);
			statement.setString(5, accessLevel);
			statement.setInt(6, id);

			log.trace("Executing database update...");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) updated!", rows));

			if (rows == 0) {
				log.error("Updating user failed, no rows affected!");
				throw new DAOEXCEPTION("Updating user failed, no rows affected!");
			} else {
				result = true;
			}
		} catch (SQLException e) {
			log.error("Updating user failed!");
			throw new DAOEXCEPTION("Updating user failed!", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		if (result == false) {
			log.info("Updating user failed, no rows affected!");
		} else {
			log.trace("Returning result...");
			log.info("User with ID#" + id + " is updated in database!");
		}
		return result;
	}

	public boolean updateByEmail(String firstName, String lastName, String email, String password, String accessLevel)
			throws DAOEXCEPTION {
		log.info("Updating user by email in database...");
		String sqlQuery = "update user set first_name = ?, last_name = ?, password = ?, access_level = ? where email = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;

		try {
			log.trace("Opening connection...");
			connection = DAOFACTORY.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, password);
			statement.setString(4, accessLevel);
			statement.setString(5, email);

			log.trace("Executing database update...");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) updated!", rows));
			
			if (rows == 0) {
				log.error("Updating user failed, no rows affected!");
				throw new DAOEXCEPTION("Updating user failed, no rows affected!");
			} else {
				result = true;
			}
		} catch (SQLException e) {
			log.error("Updating user failed!");
			throw new DAOEXCEPTION("Updating user failed!", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		if (result == false) {
			log.info("Updating user failed, no rows affected!");
		} else {
			log.trace("Returning result...");
			log.info("User with email: " + email + " is updated in database!");
		}
		return result;
	}
	

	public boolean delete(int id) throws DAOEXCEPTION {
		log.info("Deleting user by id from database...");
		String sqlQuery = "delete from user where id = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;

		try {
			log.trace("Opening connection...");
			connection = DAOFACTORY.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, id);

			log.trace("Executing database update...");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) deleted!", rows));
			
			if (rows == 0) {
				log.error("Deleting user failed, no rows affected!");
				throw new DAOException("Deleting user failed, no rows affected!");
			} else {
				result = true;
			}
		} catch (SQLException e) {
			log.error("Deleting user failed!");
			throw new DAOEXCEPTION("Deleting user failed!", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		if (result == false) {
			log.info("Deleting user failed, no rows affected!");
		} else {
			log.trace("Returning result...");
			log.info("User with ID#" + id + " is deleted from database!");
		}
		return result;
	}
}
