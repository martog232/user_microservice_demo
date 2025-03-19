package com.example.user_microservice_demo.service.user;

import com.example.user_microservice_demo.data.entity.User;
import com.example.user_microservice_demo.web.model.UserReqModel;
import com.example.user_microservice_demo.web.model.UserSimpleRespModel;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface UserService {

    User findById(Long id) throws EntityNotFoundException;

    List<UserSimpleRespModel> findAll();

    List<UserSimpleRespModel> findByName(String name);

    List<UserSimpleRespModel> findByCountry(String term);

    UserSimpleRespModel createUser(UserReqModel user);

    void deleteUserById(Long id);
}
