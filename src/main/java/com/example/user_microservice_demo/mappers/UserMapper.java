package com.example.user_microservice_demo.mappers;

import com.example.user_microservice_demo.data.entity.User;
import com.example.user_microservice_demo.service.country.CountryService;
import com.example.user_microservice_demo.web.model.UserReqModel;
import com.example.user_microservice_demo.web.model.UserSimpleRespModel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.example.user_microservice_demo.util.constants.GlobalConstants.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {

    @Mapping(target = "countryName", source = "country.name")
    UserSimpleRespModel mapToUserSimpleRespModel(User user);

    @Mapping(target = "country", expression = "java(countryService.findByCountryCode(user.countryCode()))")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User mapToUser(UserReqModel user, @Context CountryService countryService);
}
