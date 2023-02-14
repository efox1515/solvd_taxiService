package com.solvd.taxiService.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.taxiService.dao.IPassengerDao;
import com.solvd.taxiService.models.passenger.Passenger;
import com.solvd.taxiService.services.Runner;
import java.sql.Connection;
import com.solvd.taxiService.utils.ConnectionPoolA;

public class PassengerDAO extends MySQLDAO<Passenger> implements IPassengerDAO {
	final Logger logger = LogManager.getLogger(HospitalRunner.class.getName());

	private ConnectionPoolA pool = new ConnectionPoolA();

	@Override
	public Passenger getEntityById(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Passenger WHERE id = ?", 0);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				Passenger passenger = resultSetToPassenger(rs);
				return passenger;
			}
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				logger.info(e);
			}
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return null;
	}

	@Override
	public void updateEntity(Passenger entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"UPDATE Passenger SET first_name = ?, last_name = ?, WHERE id = ?");
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setLong(3, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				logger.info(e);
			}
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
	}

	@Override
	public Passenger createEntity(Passenger entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"INSERT INTO Passenger (first_name, last_name) VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				entity.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				logger.info(e);
			}
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return entity;
	}

	@Override
	public void removeEntity(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("DELETE FROM Passenger WHERE id = ?");
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				logger.info(e);
			}
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
	}

	@Override
	public List<Passenger> getAllPassengers() throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Passenger> passengers = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Passenger");
			rs = statement.executeQuery();
			while (rs.next()) {
				Passenger passenger = resultSetToPassenger(rs);
				patients.add(patient);
			}
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				logger.info(e);
			}
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return passengers;
	}

	public List<Passenger> getPassengerByParameter(String parameter, Object value) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Passenger> passengers = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Passenger WHERE " + parameter + " = ?");
			statement.setObject(1, value);
			rs = statement.executeQuery();
			while (rs.next()) {
				Passenger passenger = resultSetToPassenger(rs);
				passengers.add(passenger);
			}
		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				logger.info(e);
			}
			if (connection != null) {
				pool.releaseConnection(connection);
			}
		}
		return passengers;
	}

	private Passenger resultSetToPassenger(ResultSet rs) throws InterruptedException {
		try {
			long id = rs.getLong("id");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			return new Passenger(id, firstName, lastName);
		} catch (SQLException e) {
			logger.info(e);
		}
		return null;

	}

}
