package com.dsa360.api.serviceimpl;

import java.util.List;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.dsa360.api.dao.CustomerDao;
import com.dsa360.api.dto.CustomerDTO;
import com.dsa360.api.dto.DocumentDTO;
import com.dsa360.api.dto.LoanApplicationDTO;
import com.dsa360.api.entity.CustomerEntity;
import com.dsa360.api.entity.DocumentEntity;
import com.dsa360.api.entity.LoanApplicationEntity;
import com.dsa360.api.service.CustomerService;
import com.dsa360.api.utility.DynamicID;
import com.dsa360.api.utility.FileStorageUtility;
import com.dsa360.api.utility.ObjectConverter;

@Service

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private ObjectConverter converter;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private FileStorageUtility fileStorageUtility;

	@Override
	public String createCustomer(CustomerDTO customerDTO) {
		String customerId = DynamicID.getDynamicId();
		customerDTO.setId(customerId);

		CustomerEntity customerEntity = (CustomerEntity) converter.dtoToEntity(customerDTO);

		customerDao.createCustomer(customerEntity);

		return customerId;
	}

	@Override
	public String checkLoanEligibility(String customerId) {

		return null;
	}

	@Override
	public String customerLoanApplication(LoanApplicationDTO loanApplicationDTO) {
		String loanId = DynamicID.getDynamicId();
		loanApplicationDTO.setId(loanId);
		LoanApplicationEntity loanApplicationEntity = mapper.map(loanApplicationDTO, LoanApplicationEntity.class);

		customerDao.customerLoanApplication(loanApplicationEntity);

		return loanId;
	}

	@Override
	public void uploadDocument(String customerId, DocumentDTO documentDTO) {
		String documentId = DynamicID.getDynamicId();
		documentDTO.setId(documentId);
		boolean isStoared = fileStorageUtility.storeCustomerFile(customerId, documentDTO.getFile());
		if (isStoared) {
			DocumentEntity documentEntity = (DocumentEntity) converter.dtoToEntity(documentDTO);
			customerDao.uploadDocument(customerId, documentEntity);
		}

	}

	@Override
	public List<CustomerDTO> getAllCustomers() {

		return null;
	}

	@Override
	 @Transactional(readOnly = true)
	public CustomerEntity getCustomerById(String id) {
	    System.out.println("Transaction active: " + TransactionSynchronizationManager.isActualTransactionActive());

		CustomerEntity customerEntity = customerDao.getCustomerById(id);
		  Hibernate.initialize(customerEntity.getLoanApplications());
		return customerEntity;
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customerDTO) {

		return null;
	}

	@Override
	public void cancelCustomerLoanApplication(String id) {

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

	}

}
