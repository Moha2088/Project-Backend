package org.example.projectbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectbackend.models.organisation.dtos.CreateOrganisationDto;
import org.example.projectbackend.services.organisation.OrganisationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organisations")
@RequiredArgsConstructor
public class OrganisationController {
    
    private final OrganisationService organisationService;
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createOrganisation(@RequestBody CreateOrganisationDto dto) {
        organisationService.createOrganisation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
