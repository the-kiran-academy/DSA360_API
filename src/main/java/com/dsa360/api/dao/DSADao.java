package com.dsa360.api.dao;

import java.nio.file.Path;
import java.util.List;

import com.dsa360.api.entity.DSAApplicationEntity;
import com.dsa360.api.entity.DsaKycEntity;

public interface DSADao {
	public abstract DSAApplicationEntity getDSAById(String dsaID);

	public abstract DSAApplicationEntity dsaApplication(DSAApplicationEntity dsaRegistrationEntity);

	public abstract List<DSAApplicationEntity> getAllDsaApplication();
	
	public abstract List<String> getAllApprovedDsa();

	public abstract DSAApplicationEntity notifyReview(String registrationId, String approvalStatus, String type);

	public abstract DSAApplicationEntity systemUserKyc(DsaKycEntity dsa_KYC_Entity, List<Path> storedFilePaths);

	public abstract DsaKycEntity getDsaKycByDsaId(String dsaRegistrationId);
	
	public DSAApplicationEntity emailVerificationRequest(String dsaId, String token);

	public abstract void verifyEmail(String dsaId, String token);

}
