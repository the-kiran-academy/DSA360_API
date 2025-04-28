package com.dsa360.api.dao;

import java.util.List;

import com.dsa360.api.entity.LoanConditioEntity;

public interface LoanConditionDao {

	LoanConditioEntity createLoanCondition(LoanConditioEntity loanConditioEntity);

	LoanConditioEntity getLoanConditionById(String id);

	LoanConditioEntity getLoanConditionByBankLoanType(String bankName, String loanType);

	List<LoanConditioEntity> getAllLoanConditions();

	LoanConditioEntity updateLoanCondition(LoanConditioEntity loanConditioEntity);

	void deleteLoanCondition(String id);

	List<String> getAllBanksNames();

	double getIntrestRate(String bankName);
}
