package com.example.user_microservice_demo.mappers;

import com.example.user_microservice_demo.data.entity.User;
import com.example.user_microservice_demo.web.model.UserSimpleRespModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "countryName", source = "country.name")
    UserSimpleRespModel mapToUserSimpleRespModel(User user);
}
