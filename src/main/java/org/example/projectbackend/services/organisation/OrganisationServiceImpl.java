package org.example.projectbackend.services.organisation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.projectbackend.config.CurrentUserProvider;
import org.example.projectbackend.models.organisation.Organisation;
import org.example.projectbackend.models.organisation.dtos.CreateOrganisationDto;
import org.example.projectbackend.models.user.User;
import org.example.projectbackend.repositories.OrganisationRepository;
import org.example.projectbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganisationServiceImpl implements OrganisationService {

    private final OrganisationRepository organisationRepository;
    private final UserRepository userRepository;
    private final CurrentUserProvider userProvider;

    @Override
    @Transactional
    public void createOrganisation(CreateOrganisationDto dto) {
        Organisation organisation = new Organisation();
        organisation.setName(dto.name());
        organisationRepository.save(organisation);
        
        User user = userProvider.getCurrentUser();
        
        user.setOrganisation(organisation);
        userRepository.save(user);
    }
}
