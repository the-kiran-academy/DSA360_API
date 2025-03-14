package com.dsa360.api.daoimpl;

import java.nio.file.Path;
import java.util.List;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dsa360.api.constants.ReviewType;
import com.dsa360.api.dao.DSADao;
import com.dsa360.api.entity.DSAApplicationEntity;
import com.dsa360.api.entity.DsaKycEntity;
import com.dsa360.api.entity.SystemUserEntity;
import com.dsa360.api.exceptions.ResourceAlreadyExistsException;
import com.dsa360.api.exceptions.ResourceNotFoundException;
import com.dsa360.api.exceptions.SomethingWentWrongException;
import com.dsa360.api.utility.FileStorageUtility;

/**
 * @author RAM
 *
 */
@Repository
public class DSADaoImpl implements DSADao {

	private static final Logger logger = LoggerFactory.getLogger(DSADaoImpl.class);

	@Autowired
	private SessionFactory factory;

	@Override
	public DSAApplicationEntity getDSAById(String dsaID) {
		String dataNotFound = "Data not found with id = ";
		DSAApplicationEntity dsaRegistrationEntity = null;
		try (Session session = factory.openSession()) {
			dsaRegistrationEntity = session.get(DSAApplicationEntity.class, dsaID);

			if (dsaRegistrationEntity != null) {
				return dsaRegistrationEntity;
			} else {
				throw new ResourceNotFoundException(dataNotFound + dsaID);
			}

		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			logger.error(dataNotFound + dsaID);
			throw new ResourceNotFoundException(dataNotFound + dsaID);
		}

		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred during get DSA with id = " + dsaID, e);
			throw new SomethingWentWrongException("Exception occurred during get DSA with id = " + dsaID);
		}
	}

	@Override
	public DSAApplicationEntity dsaApplication(DSAApplicationEntity dsaRegistrationEntity) {
		Transaction transaction = null;

		try (Session session = factory.openSession()) {

			transaction = session.beginTransaction();
			session.save(dsaRegistrationEntity);
			transaction.commit();
			logger.info("DSA registration successful for: {}",
					dsaRegistrationEntity.getDsaApplicationId() + " " + dsaRegistrationEntity.getFirstName());

			return dsaRegistrationEntity;
		} catch (PersistenceException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace(); // for dev env
			logger.error("Duplicate entry error occurred during DSA registration - check unique fields");
			throw new ResourceAlreadyExistsException(
					"Duplicate entry error occurred during DSA registration - check unique fields");
		}

		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			logger.error("Exception occurred during DSA registration", e);
			throw new SomethingWentWrongException("Something went wrong during DSA application");
		}

	}

	@Override
	public DSAApplicationEntity notifyReview(String id, String approvalStatus, String type) {
		Transaction transaction = null;
		final String dataNotFound = "Data Not Found";
		try (Session session = factory.openSession()) {

			if (ReviewType.APPLICATION.getValue().equals(type)) {
				DSAApplicationEntity dsaRegistrationEntity = session.get(DSAApplicationEntity.class, id);
				if (dsaRegistrationEntity != null) {
					transaction = session.beginTransaction();
					dsaRegistrationEntity.setApprovalStatus(approvalStatus);
					session.update(dsaRegistrationEntity);
					transaction.commit();
					return dsaRegistrationEntity;
				} else {
					throw new ResourceNotFoundException(dataNotFound);
				}

			} else if (ReviewType.KYC.getValue().equals(type)) {
				DsaKycEntity dsa_KYC_Entity = session.get(DsaKycEntity.class, id);

				if (dsa_KYC_Entity != null) {
					transaction = session.beginTransaction();
					dsa_KYC_Entity.setApprovalStatus(approvalStatus);
					session.update(dsa_KYC_Entity);
					transaction.commit();

					return getDSAById(dsa_KYC_Entity.getDsaApplicationId().getDsaApplicationId());
				} else {
					throw new ResourceNotFoundException(dataNotFound);
				}

			} else {
				throw new SomethingWentWrongException("Type mismatch to update review = " + type);
			}

		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Data not found ");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred during notify registration review status", e);
			throw new SomethingWentWrongException("Exception occurred during notify registration review status");
		}

	}

	@Override
	public boolean systemUserKyc(DsaKycEntity dsa_KYC_Entity, List<Path> storedFilePaths) {
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();

			session.saveOrUpdate(dsa_KYC_Entity);
			transaction.commit();
			return true;
		}

		catch (Exception e) {

			// Rollback files if any error occurs in DAO layer
			FileStorageUtility.revokeAllFiles(storedFilePaths);
			e.printStackTrace();
			logger.error("Exception occurred during save KYC details in DB ", e);
			throw new SomethingWentWrongException("Exception occurred during save KYC detailsin DB");
		}

	}

	@Override
	public DsaKycEntity getDsaKycByDsaId(String dsaApplicationId) {
		try (Session session = factory.openSession()) {

			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(DsaKycEntity.class, dsaApplicationId);
			criteria.add(Restrictions.eq("dsaApplicationId.dsaApplicationId", dsaApplicationId));
			@SuppressWarnings("unchecked")
			List<DsaKycEntity> list = criteria.list();
			if (!list.isEmpty()) {
				return (DsaKycEntity) list.get(0);

			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred fetch KYC data by registration id", e);
			throw new SomethingWentWrongException("Exception occurred fetch KYC data by registration id");
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<DSAApplicationEntity> getAllDsaApplication() {
		List<DSAApplicationEntity> list = null;
		try (Session session = factory.openSession()) {

			Criteria criteria = session.createCriteria(DSAApplicationEntity.class);
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred during get all DSA registration", e);
			throw new SomethingWentWrongException("Exception occurred during get all DSA registration");
		}
		return list;
	}

	@Override
	public DSAApplicationEntity emailVerificationRequest(String dsaId, String token) {
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();

			DSAApplicationEntity dsaEntity = session.get(DSAApplicationEntity.class, dsaId);
			dsaEntity.setEmailVerificationToken(token);
			dsaEntity.setEmailVerified(false);

			session.update(dsaEntity);
			transaction.commit();
			return dsaEntity;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred during email verification request", e);
			throw new SomethingWentWrongException("Something went wrong  during email verification request");
		}

	}

	@Override
	public void verifyEmail(String dsaId, String token) {
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();

			DSAApplicationEntity dsaEntity = session.get(DSAApplicationEntity.class, dsaId);
			if (dsaEntity.getEmailVerificationToken().equals(token)) {
				dsaEntity.setEmailVerified(true);
				transaction.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred during email verification ", e);
			throw new SomethingWentWrongException("Something went wrong  during email verification ");
		}

	}

	@Override
	public List<String> getAllApprovedDsa() {
		List<String> dsaIdList = null;
		try (Session session = factory.openSession()) {

			Criteria criteria = session.createCriteria(DSAApplicationEntity.class);
			criteria.setProjection(Projections.property("dsaApplicationId"));
			criteria.add(Restrictions.and(Restrictions.eq("approvalStatus", "Approved"),
					Restrictions.eq("emailVerified", true)));

			dsaIdList = criteria.list();

//			Criteria criteria2 = session.createCriteria(SystemUserEntity.class);
//			criteria2.setProjection(Projections.property("dsaApplicationId.dsaApplicationId"));
//			List<SystemUserEntity> list2 = criteria2.list();

			// dsaIdList.removeAll(list2);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred during email verification ", e);
			throw new SomethingWentWrongException("Something went wrong  during email verification ");

		}
		return dsaIdList;
	}

}
