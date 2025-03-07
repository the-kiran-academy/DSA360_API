package com.dsa360.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_documents")
public class DocumentEntity {

    @Id
    private String id;

    private String documentName;
    private String documentType; // e.g., "ID Proof", "Income Proof"
    private String status; // e.g., "Uploaded", "Verified", "Rejected"

    // Many Documents belong to one Customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

}
