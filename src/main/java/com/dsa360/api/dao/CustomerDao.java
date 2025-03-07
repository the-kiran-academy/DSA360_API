package com.dsa360.api.dao;

import java.util.List;

import com.dsa360.api.dto.DocumentDTO;
import com.dsa360.api.entity.CustomerEntity;
import com.dsa360.api.entity.DocumentEntity;
import com.dsa360.api.entity.LoanApplicationEntity;

public interface CustomerDao {
	public abstract void createCustomer(CustomerEntity customerEntity);

	public abstract String checkLoanEligibility(String customerId);

	public abstract void customerLoanApplication(LoanApplicationEntity loanApplicationEntity);

	public abstract void uploadDocument(String customerId, DocumentEntity documentEntity);

	public abstract List<CustomerEntity> getAllCustomers();

	public abstract CustomerEntity getCustomerById(String id);

	public abstract CustomerEntity updateCustomer(CustomerEntity customerEntity);

	public abstract void cancelCustomerLoanApplication(String id);

	public abstract List<DocumentDTO> getDocumentsByCustomerId(String customerId);

	public abstract DocumentDTO getDocumentById(String customerId, String documentId);

	public abstract void deleteDocument(String customerId, String documentId);

}
