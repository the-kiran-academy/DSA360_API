package com.dsa360.api.daoimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dsa360.api.dao.CustomerDao;
import com.dsa360.api.dto.DocumentDTO;
import com.dsa360.api.entity.ContactUsEntity;
import com.dsa360.api.entity.CustomerEntity;
import com.dsa360.api.entity.DocumentEntity;
import com.dsa360.api.entity.LoanApplicationEntity;
import com.dsa360.api.exceptions.SomethingWentWrongException;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	private static final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);

	private SessionFactory sessionFactory;

	public CustomerDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void createCustomer(CustomerEntity customerEntity) {

		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.save(customerEntity);

			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
			throw ex;
		}
	}

	@Override
	public CustomerEntity getCustomerById(String id) {
	    try (Session session = sessionFactory.openSession()) {
	        Transaction tx = session.beginTransaction();

	        // Step 1: Load the CustomerEntity along with DSA Agent only
	        String customerQuery = "FROM CustomerEntity c LEFT JOIN FETCH c.dsaAgentId WHERE c.id = :id";
	        CustomerEntity customer = session.createQuery(customerQuery, CustomerEntity.class)
	                                         .setParameter("id", id)
	                                         .uniqueResult();

	        if (customer != null) {
	            // Step 2: Load loan applications separately
	            String loanQuery = "SELECT l FROM LoanApplicationEntity l WHERE l.customer.id = :id";
	            Set<LoanApplicationEntity> loanApplications = new HashSet<>(
	                session.createQuery(loanQuery, LoanApplicationEntity.class)
	                       .setParameter("id", id)
	                       .getResultList()
	            );
	            customer.setLoanApplications(loanApplications);

	            // Step 3: Load documents separately
	            String docQuery = "SELECT d FROM DocumentEntity d WHERE d.customer.id = :id";
	            Set<DocumentEntity> documents = new HashSet<>(
	                session.createQuery(docQuery, DocumentEntity.class)
	                       .setParameter("id", id)
	                       .getResultList()
	            );
	            customer.setDocuments(documents);
	        }

	        tx.commit();
	        return customer;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}


	@Override
	public void customerLoanApplication(LoanApplicationEntity loanApplicationEntity) {

		try (var session = sessionFactory.openSession()) {
			session.save(loanApplicationEntity);
			session.beginTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred during loan application for loan ID {} ", loanApplicationEntity.getId());
			throw new SomethingWentWrongException(
					"Exception occurred during loan application for loan Id " + loanApplicationEntity.getId());
		}
	}


	@Override
	public String checkLoanEligibility(String customerId) {

		return null;
	}

	
	@Override
	public void uploadDocument(String customerId, DocumentEntity documentEntity) {
		try (var session = sessionFactory.openSession()) {
			session.save(documentEntity);
			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred during upload customer document {}", customerId);
			throw new SomethingWentWrongException("Exception occurred during upload customer document " + customerId);
		}

	}

	@Override
	public List<CustomerEntity> getAllCustomers() {

		return null;
	}

	@Override
	public CustomerEntity updateCustomer(CustomerEntity customerEntity) {

		return null;
	}

	@Override
	public void cancelCustomerLoanApplication(String id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<DocumentDTO> getDocumentsByCustomerId(String customerId) {

		return null;
	}

	@Override
	public DocumentDTO getDocumentById(String customerId, String documentId) {

		return null;
	}

	@Override
	public void deleteDocument(String customerId, String documentId) {
		throw new UnsupportedOperationException();

	}

	@Override
	public String contactUs(ContactUsEntity contactUsEntity) {
		try (var session = sessionFactory.openSession()) {
			session.save(contactUsEntity);
			session.beginTransaction().commit();
			return "Data saved successfully";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred during contact us {}", contactUsEntity.getId());
			throw new SomethingWentWrongException("Exception occurred during contact us " + contactUsEntity.getId());
		}

	}

}
