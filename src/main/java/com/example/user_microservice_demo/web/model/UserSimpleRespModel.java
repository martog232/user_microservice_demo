package com.example.user_microservice_demo.web.model;

import lombok.Builder;

@Builder
public record UserSimpleRespModel(String name, String countryName) {
}
