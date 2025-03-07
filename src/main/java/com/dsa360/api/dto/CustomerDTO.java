package com.dsa360.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO {
    private String id;
    
    private String name;
    private String email;
    private String phoneNumber;
    private String permanentAddress;
    private String currentAddress;

    private String dsaAgentId;
    private List<DocumentDTO> documents;
    private List<LoanApplicationDTO> loanApplications;
}
