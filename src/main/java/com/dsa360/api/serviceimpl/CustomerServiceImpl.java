package com.dsa360.api.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dsa360.api.dao.CustomerDao;
import com.dsa360.api.dto.ContactUsDTO;
import com.dsa360.api.dto.CustomerDTO;
import com.dsa360.api.dto.DocumentDTO;
import com.dsa360.api.dto.LoanApplicationDTO;
import com.dsa360.api.entity.ContactUsEntity;
import com.dsa360.api.entity.CustomerEntity;
import com.dsa360.api.entity.DocumentEntity;
import com.dsa360.api.entity.LoanApplicationEntity;
import com.dsa360.api.service.CustomerService;
import com.dsa360.api.utility.DynamicID;
import com.dsa360.api.utility.FileStorageUtility;
import com.dsa360.api.utility.ObjectConverter;

@Service

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	private ObjectConverter converter;

	private ModelMapper mapper;

	private FileStorageUtility fileStorageUtility;

	public CustomerServiceImpl(CustomerDao customerDao, ObjectConverter converter, ModelMapper mapper,
			FileStorageUtility fileStorageUtility) {
		super();
		this.customerDao = customerDao;
		this.converter = converter;
		this.mapper = mapper;
		this.fileStorageUtility = fileStorageUtility;
	}

	@Override
	public String createCustomer(CustomerDTO customerDTO) {
		String customerId = DynamicID.getGeneratedId();
		customerDTO.setId(customerId);

		var customerEntity = (CustomerEntity) converter.dtoToEntity(customerDTO);

		customerDao.createCustomer(customerEntity);

		return customerId;
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerEntity getCustomerById(String id) {
		var customerEntity = customerDao.getCustomerById(id);
		return customerEntity;
	}

	@Override
	public String customerLoanApplication(LoanApplicationDTO loanApplicationDTO) {
		String loanId = DynamicID.getGeneratedId();
		loanApplicationDTO.setId(loanId);
		loanApplicationDTO.setApplicationDate(LocalDate.now());
		var loanApplicationEntity = mapper.map(loanApplicationDTO, LoanApplicationEntity.class);

		customerDao.customerLoanApplication(loanApplicationEntity);

		return loanId;
	}

	@Override
	public String checkLoanEligibility(String customerId) {

		return null;
	}

	@Override
	public void uploadDocument(String customerId, DocumentDTO documentDTO) {

		DocumentEntity document = customerDao
				.getDocumentByTypeAndCustomerId(documentDTO.getDocumentType().getDisplayName(), customerId);

		if (document == null) {
			String documentId = DynamicID.getGeneratedId();
			documentDTO.setId(documentId);

		} else {
			documentDTO.setId(document.getId());
		}

		boolean isStoared = fileStorageUtility.storeCustomerFile(customerId, documentDTO.getFile());
		if (isStoared) {
			var documentEntity = (DocumentEntity) converter.dtoToEntity(documentDTO);
			customerDao.uploadDocument(customerId, documentEntity);
		}

	}

	@Override
	public List<CustomerEntity> getAllCustomers() {
		List<CustomerEntity> allCustomers = customerDao.getAllCustomers();
		return allCustomers;
	}

	@Override
	public List<CustomerEntity> getCustomersByDsaAgentId(String dsaAgentId) {
		return customerDao.getCustomersByDsaAgentId(dsaAgentId);
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

	@Override
	public String contactUs(ContactUsDTO contactUsDTO) {
		String id = DynamicID.getGeneratedId();
		contactUsDTO.setId(id);
		var contactUsEntity = mapper.map(contactUsDTO, ContactUsEntity.class);
		return customerDao.contactUs(contactUsEntity);
	}

}
