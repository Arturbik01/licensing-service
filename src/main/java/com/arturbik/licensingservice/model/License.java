package com.arturbik.licensingservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class License {

    private int id;
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
}
