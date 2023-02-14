package com.solvd.taxiService.dao.mybatis;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.solvd.taxiService.dao.IDestinationDao;
import com.solvd.taxiService.models.Destination;

public class DestinationDao implements IDestinationDao {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Room getEntityById(long id) throws InterruptedException {
		return sqlSession.selectOne("Destination.getDestinationById", id);
	}

	@Override
	public void updateEntity(Destination destination) throws InterruptedException {
		sqlSession.update("Destination.updateDestination", room);
	}

	@Override
	public Room createEntity(Destination destination) throws InterruptedException {
		sqlSession.insert("Destination.insertDestination", room);
		return room;
	}

	@Override
	public void removeEntity(long id) throws InterruptedException {
		sqlSession.delete("Destination.deleteDestination", id);
	}

	@Override
	public List<Room> getAllDestination() throws InterruptedException {
		return sqlSession.selectList("Destination.getAllDestinations");
	}

	@Override
	public List<Room> getDestinationsByByParameter(String parameter, Object value) throws InterruptedException {
		return sqlSession.selectList("Destination.getDestinationsByByParameter", value);
	}
}