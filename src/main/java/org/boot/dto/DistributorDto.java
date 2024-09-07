package org.boot.dto;

import lombok.Data;

@Data
public class DistributorDto {
    private Integer distributorId;
    private String distributorCode;
    private String distributorName;
    private String distributorAddress;
    private String contactNo;
    private String emailAddress;
}
