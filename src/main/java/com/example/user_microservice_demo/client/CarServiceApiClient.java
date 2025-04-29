package com.example.user_microservice_demo.client;

import com.example.user_microservice_demo.web.model.CarRespModel;

import java.util.List;

public interface CarServiceApiClient {

    List<CarRespModel> getCarsByOwner(Long userId);
}
