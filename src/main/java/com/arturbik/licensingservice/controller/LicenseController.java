package com.arturbik.licensingservice.controller;

import com.arturbik.licensingservice.model.License;
import com.arturbik.licensingservice.service.LicenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("v1/organization/{organizationId}/license")
public class LicenseController {

    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable String organizationId, @PathVariable String licenseId) {

        License license = licenseService.getLicense(licenseId, organizationId);
        return ResponseEntity.ok(license);
    }

    @PutMapping()
    public ResponseEntity<String> updateLicense(@PathVariable String organizationId, @RequestBody License license,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.updateLicense(license, organizationId, locale));
    }

    @PostMapping()
    public ResponseEntity<String> createLicense(@PathVariable String organizationId, @RequestBody License license,
                                                @RequestHeader(value = "Accept-Language", required = false)Locale locale) {
        return ResponseEntity.ok(licenseService.createLicense(license, organizationId, locale));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteLicense(@PathVariable String organizationId, @RequestParam String licenseId,
                                                @RequestHeader(value = "Accept-Language", required = false)Locale locale) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId, locale));
    }

}
