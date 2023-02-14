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

import com.solvd.taxiService.dao.IPaymentDao;
import com.solvd.taxiService.models.payment.Payment;
import com.solvd.taxiService.services.Runner;
import java.sql.Connection;
import com.solvd.taxiService.utils.ConnectionPoolA;

public class PaymentDao extends MySQLDao<Payment> implements IPaymentDAO {
	final Logger logger = LogManager.getLogger(HospitalRunner.class.getName());

	private ConnectionPoolA pool = new ConnectionPoolA();

	@Override
	public Payment getEntityById(long id) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Payment WHERE id = ?", 0);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				Paymnet payment = resultSetToPayment(rs);
				return payment;
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
	public void updateEntity(Payment entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"UPDATE Payment SET creditCard_number = ?, security_code = ?, expiration_date = ?, WHERE id = ?");
			statement.setLong(1, entity.getCreditCardNumber());
			statement.setLong(2, entity.getSecurityCode());
			statement.setDate(3, entity.getExpirationDate());
			statement.setLong(4, entity.getId());
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
	public Payment createEntity(Payment entity) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(
					"INSERT INTO Payment (creditCard_number, security_code, expiration_date) VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, entity.getCreditCardNumber());
			statement.setLong(2, entity.getSecurityCode());
			statement.setDate(3, entity.getExpirationDate());
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
			statement = connection.prepareStatement("DELETE FROM Payment WHERE id = ?");
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
	public List<Payment> getAllPayments() throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Payment> payments = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Payment");
			rs = statement.executeQuery();
			while (rs.next()) {
				Payment payments = resultSetToPayment(rs);
				payments.add(payment);
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
		return payments;
	}

	public List<Payment> getPaymentByParameter(String parameter, Object value) throws InterruptedException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Payment> payments = new ArrayList<>();
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM Payment WHERE " + parameter + " = ?");
			statement.setObject(1, value);
			rs = statement.executeQuery();
			while (rs.next()) {
				Payment payment = resultSetToPayment(rs);
				payments.add(payment);
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
		return payments;
	}

	private Payment resultSetToPayment(ResultSet rs) throws InterruptedException {
		try {
			long id = rs.getLong("id");
			Long creditCardNumber = rs.getString("creditCardNumber");
			Long securityCode = rs.getString("securityCode");
			Date expirationDate = rs.getString("expirationDate");
			return new Passenger(id, creditCardNumber, securityCode, expirationDate);
		} catch (SQLException e) {
			logger.info(e);
		}
		return null;

	}

}