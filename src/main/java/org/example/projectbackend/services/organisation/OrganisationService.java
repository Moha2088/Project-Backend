package org.example.projectbackend.services.organisation;

import org.example.projectbackend.models.organisation.dtos.CreateOrganisationDto;

public interface OrganisationService {
    void createOrganisation(CreateOrganisationDto dto);
}
