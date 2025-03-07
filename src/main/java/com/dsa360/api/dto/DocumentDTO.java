package com.dsa360.api.dto;

import org.springframework.web.multipart.MultipartFile;
import com.dsa360.api.constants.DocumentStatus;
import com.dsa360.api.constants.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {

	private String id;
	private DocumentType documentType;
	private String documentName;
	private String status = DocumentStatus.UPLOADED.getValue();
	private String customerId;

	private MultipartFile file;
}
