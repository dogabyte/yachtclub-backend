package com.yachtclub.backend.services.validation;

import com.yachtclub.backend.exceptions.ResourceNotFoundException;
import com.yachtclub.backend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Centralized validation service for entity existence checks.
 * Follows DRY principle by avoiding repeated validation logic across services.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EntityValidationService {

    private final ZoneRepository zoneRepository;
    private final BoatRepository boatRepository;
    private final PartnerRepository partnerRepository;
    private final EmployeeRepository employeeRepository;
    private final AdministratorRepository administratorRepository;

    /**
     * Validate that a zone exists by ID.
     * 
     * @param zoneId Zone ID
     * @throws ResourceNotFoundException if zone doesn't exist
     */
    public void validateZoneExists(Long zoneId) {
        if (!zoneRepository.existsById(zoneId)) {
            throw new ResourceNotFoundException("Zone", "id", zoneId);
        }
    }

    /**
     * Validate that a boat exists by vessel number.
     * 
     * @param vesselNumber Vessel registration number
     * @throws ResourceNotFoundException if boat doesn't exist
     */
    public void validateBoatExists(String vesselNumber) {
        if (!boatRepository.findByVesselNumber(vesselNumber).isPresent()) {
            throw new ResourceNotFoundException("Boat", "vesselNumber", vesselNumber);
        }
    }

    /**
     * Validate that a boat exists by ID.
     * 
     * @param boatId Boat ID
     * @throws ResourceNotFoundException if boat doesn't exist
     */
    public void validateBoatExistsById(Long boatId) {
        if (!boatRepository.existsById(boatId)) {
            throw new ResourceNotFoundException("Boat", "id", boatId);
        }
    }

    /**
     * Validate that a partner exists by ID.
     * 
     * @param partnerId Partner ID
     * @throws ResourceNotFoundException if partner doesn't exist
     */
    public void validatePartnerExists(Long partnerId) {
        if (!partnerRepository.existsById(partnerId)) {
            throw new ResourceNotFoundException("Partner", "id", partnerId);
        }
    }

    /**
     * Validate that an employee exists by ID.
     * 
     * @param employeeId Employee ID
     * @throws ResourceNotFoundException if employee doesn't exist
     */
    public void validateEmployeeExists(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
    }

    /**
     * Validate that an administrator exists by ID.
     * 
     * @param adminId Administrator ID
     * @throws ResourceNotFoundException if administrator doesn't exist
     */
    public void validateAdministratorExists(Long adminId) {
        if (!administratorRepository.existsById(adminId)) {
            throw new ResourceNotFoundException("Administrator", "id", adminId);
        }
    }

    /**
     * Validate that a zone exists by name.
     * 
     * @param zoneName Zone name
     * @throws ResourceNotFoundException if zone doesn't exist
     */
    public void validateZoneExistsByName(String zoneName) {
        if (!zoneRepository.findByName(zoneName).isPresent()) {
            throw new ResourceNotFoundException("Zone", "name", zoneName);
        }
    }
}
