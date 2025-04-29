package com.example.user_microservice_demo.service.country;

import com.example.user_microservice_demo.data.entity.Country;

public interface CountryService {

    Country findByCountryCode(String countryCode);
}
