package com.dsa360.api.daoimpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.dsa360.api.dao.SystemUserDao;
import com.dsa360.api.entity.SystemUserEntity;
import com.dsa360.api.exceptions.SomethingWentWrongException;
import com.dsa360.api.security.CustomUserDetail;

/**
 * @author RAM
 *
 */
@Repository
public class SystemUserDaoImpl implements SystemUserDao {
	private static Logger log = LogManager.getLogger(SystemUserDaoImpl.class);

	@Autowired
	private SessionFactory factory;

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Override
	public CustomUserDetail loadUserByUserId(String userId) {

		CustomUserDetail user = null;
		SystemUserEntity usr = null;
		try (Session session = factory.openSession()) {
			session.setDefaultReadOnly(false);
			usr = session.get(SystemUserEntity.class, userId);
			if (usr != null) {
				user = new CustomUserDetail();
				user.setUsername(userId);
				user.setPassword(usr.getPassword());
				user.setRoles(usr.getRoles());
				user.setStatus(usr.getStatus());
			}
			log.info("Loaded User ={}", userId);
		} catch (Exception e) {
			log.error("Exception = {}", e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public SystemUserEntity getSystemUserByUserName(String userName) {

		try (Session session = factory.openSession()) {
			return session.get(SystemUserEntity.class, userName);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred during get system user with username = " + userName, e);
			throw new SomethingWentWrongException("Something went wrong during retrive user = " + userName);
		}

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<SystemUserEntity> getAllSystemUser() {
		try (Session session = factory.openSession()) {
			return session.createCriteria(SystemUserEntity.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred during get all system user = " + e);
			throw new SomethingWentWrongException("Something went wrong during retrive all user");
		}

	}

	@Override
	public void updateSystemUser(SystemUserEntity userEntity) {
		Transaction transaction = null; 
		try(Session session = factory.openSession()) {
			transaction=session.beginTransaction();
			session.update(userEntity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("Exception occurred during update system user profile = " + e);
			throw new SomethingWentWrongException("Something went wrong during update system user profile");
		}	
		

	}

}
