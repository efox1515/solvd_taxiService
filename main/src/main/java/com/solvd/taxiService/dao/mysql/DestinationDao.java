package com.solvd.taxiService.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.dao.IRoomDAO;
import com.solvd.hospitalsystem.models.Room;
import java.sql.Connection;
import com.solvd.hospitalsystem.utils.ConnectionPoolA;

public class DestinationDao extends MySQLDao<Destination> implements IDestinationDAO {

	final Logger logger = LogManager.getLogger(RoomDAO.class.getName());

	private ConnectionPoolA pool = new ConnectionPoolA();

	@Override
	public Destination getEntityById(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Destination WHERE id = ?", 0);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				Destination destination = resultSetToRoom(rs);
				return destination;
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
	public void updateEntity(Destination entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"UPDATE Destination SET address = ?, WHERE id = ?");
			statement.setString(1, entity.getAddress());
			statement.setLong(5, entity.getId());
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
	public Destination createEntity(Destination entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"INSERT INTO Destination (address) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getAddress());
			statement.executeUpdate();

			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				long id = rs.getLong(1);
				entity.setId(id);
				return entity;
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
	public void removeEntity(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("DELETE FROM Destination WHERE id = ?");
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
	public List<Room> getAllDestinations() throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Destination> destinations = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Destination", 0);
			rs = statement.executeQuery();
			while (rs.next()) {
				rooms.add(resultSetToDestination(rs));
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
		return destinations;
	}

	@Override
	public List<Destination> getRoomsByByParameter(String parameter, Object value) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Destination> destinations = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Destination WHERE " + parameter + " = ?", 0);
			statement.setObject(1, value);
			rs = statement.executeQuery();
			while (rs.next()) {
				rooms.add(resultSetToDestination(rs));
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
		return destinations;
	}

	private Room resultSetToDestination(ResultSet result) throws SQLException {
		long id = result.getLong("id");
		String destination = result.getString("address");
		return new Destination(id, address);
	}

}