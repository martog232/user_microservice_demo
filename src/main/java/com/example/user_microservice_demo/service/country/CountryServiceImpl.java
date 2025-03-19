package com.example.user_microservice_demo.service.country;

import com.example.user_microservice_demo.data.entity.Country;
import com.example.user_microservice_demo.data.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.user_microservice_demo.util.constants.GlobalConstants.COUNTRY_NOT_FOUND_EXCEPTION_MSG;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;


    @Override
    public Country findByCountryCode(String countryCode) {
        return countryRepository.findByCode(countryCode).orElseThrow(() ->
                new EntityNotFoundException(COUNTRY_NOT_FOUND_EXCEPTION_MSG.formatted(countryCode)));
    }
}
