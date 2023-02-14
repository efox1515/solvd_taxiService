package com.solvd.taxiService.dao.mybatis;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.solvd.taxiService.dao.IPaymentDao;
import com.solvd.taxiService.models.payment.Payment;

public class PaymentDao implements IPaymentDao {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Appointment getEntityById(long id) throws InterruptedException {
		return sqlSession.selectOne("Payment.getPaymentById", id);
	}

	@Override
	public void updateEntity(Payment payment) throws InterruptedException {
		sqlSession.update("Payment.updatePaymentt", payment);
	}

	@Override
	public Appointment createEntity(Payment payment) throws InterruptedException {
		sqlSession.insert("Appointment.insertAppointment", payment);
		return payment;
	}

	@Override
	public void removeEntity(long id) throws InterruptedException {
		sqlSession.delete("Payment.deletePayment", id);
	}

	@Override
	public List<Appointment> getAllPayments() throws InterruptedException {
		return sqlSession.selectList("Payment.getAllPayments);
	}

	@Override
	public List<Appointment> getPaymentsByParameter(String parameter, Object value) throws InterruptedException {
		return sqlSession.selectList("Payment.getPaymentsByParameter", value);
	}
}
