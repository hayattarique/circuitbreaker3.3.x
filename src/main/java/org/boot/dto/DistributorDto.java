package org.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DistributorDto {
    private Integer distributorId;
    private String distributorCode;
    private String distributorName;
    private String distributorAddress;
    private String contactNo;
    private String emailAddress;
}
