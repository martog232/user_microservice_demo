package com.example.user_microservice_demo.service.country;

import com.example.user_microservice_demo.data.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;


}
