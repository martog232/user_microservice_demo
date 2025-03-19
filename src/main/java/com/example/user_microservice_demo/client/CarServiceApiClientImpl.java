package com.example.user_microservice_demo.client;

import com.example.user_microservice_demo.config.ApplicationProperties;
import com.example.user_microservice_demo.web.model.CarRespModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static com.example.user_microservice_demo.util.constants.GlobalConstants.CARS_BY_OWNER_FETCH_URI;
import static com.example.user_microservice_demo.util.constants.GlobalConstants.FAILED_CAR_FETCH_LOG_MSG;


@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceApiClientImpl implements CarServiceApiClient {

    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    @Override
    public List<CarRespModel> getCarsByOwner(Long userId) {
        String carServiceUrl = CARS_BY_OWNER_FETCH_URI.formatted(applicationProperties.getCarServiceUrl(), userId);

        try {
            ResponseEntity<List<CarRespModel>> resp = restTemplate
                    .exchange(carServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });

            return resp.getBody();

        } catch (Exception e) {
            log.error(FAILED_CAR_FETCH_LOG_MSG, userId, e.toString());

            return Collections.emptyList();
        }
    }
}
