package com.arturbik.licensingservice.controller;

import com.arturbik.licensingservice.model.License;
import com.arturbik.licensingservice.service.LicenseService;
import org.springframework.boot.hateoas.autoconfigure.HateoasProperties;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.config.HateoasConfiguration;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
        license.add(linkTo(methodOn(LicenseController.class).getLicense(organizationId, license.getLicenseId())).withSelfRel());
        license.add(linkTo(methodOn(LicenseController.class).createLicense(organizationId, license, null)).withRel("createLicense"));
        license.add(linkTo(methodOn(LicenseController.class).updateLicense(organizationId, license, null)).withRel("updateLicense"));
        license.add(linkTo(methodOn(LicenseController.class).deleteLicense(organizationId, license.getLicenseId(), null)).withRel("deleteLicense"));
        return ResponseEntity.ok(license);
    }

    @PutMapping()
    public ResponseEntity<String> updateLicense(@PathVariable String organizationId, @RequestBody License license,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.updateLicense(license, organizationId, locale));
    }

    @PostMapping()
    public ResponseEntity<String> createLicense(@PathVariable String organizationId, @RequestBody License license,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.createLicense(license, organizationId, locale));
    }

    @DeleteMapping(value = "/{license}")
    public ResponseEntity<String> deleteLicense(@PathVariable String organizationId, @PathVariable(name = "license") String licenseId,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        if (locale == null) {
            locale = LocaleContextHolder.getLocale();
        }
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId, locale));
    }

}
