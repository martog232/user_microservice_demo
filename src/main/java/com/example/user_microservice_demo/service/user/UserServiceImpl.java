package com.example.user_microservice_demo.service.user;

import com.example.user_microservice_demo.data.entity.User;
import com.example.user_microservice_demo.data.repository.UserRepository;
import com.example.user_microservice_demo.mappers.UserMapper;
import com.example.user_microservice_demo.service.UserProducerService;
import com.example.user_microservice_demo.service.country.CountryService;
import com.example.user_microservice_demo.web.model.UserReqModel;
import com.example.user_microservice_demo.web.model.UserSimpleRespModel;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.user_microservice_demo.util.constants.GlobalConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CountryService countryService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserProducerService userProducerService;

    @Override
    public User findById(Long id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_EXCEPTION_MSG.formatted(id)));
    }

    @Override
    public List<UserSimpleRespModel> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToUserSimpleRespModel)
                .toList();
    }

    @Override
    public List<UserSimpleRespModel> findByName(String name) {
        return userRepository.findAllByName(name).stream()
                .map(userMapper::mapToUserSimpleRespModel)
                .toList();
    }

    @Override
    public List<UserSimpleRespModel> findByCountry(String term) {
        return userRepository.findAllByCountryTerm(term).stream()
                .map(userMapper::mapToUserSimpleRespModel)
                .toList();
    }

    @Override
    @Transactional
    public UserSimpleRespModel createUser(UserReqModel userReqModel) {
        log.info(CREATING_USER_LOG_MSG, userReqModel.name());

        User user = userMapper.mapToUser(userReqModel, countryService);

        User savedUser = userRepository.save(user);
        log.info(USER_CREATED_SUCCESSFULLY_LOG_MSG, savedUser.getId());

        return userMapper.mapToUserSimpleRespModel(savedUser);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            log.warn(USER_FOR_DELETE_NOT_FOUND_LOG_MSG, id);
            throw new EntityNotFoundException(USER_NOT_FOUND_EXCEPTION_MSG.formatted(id));
        }

        userRepository.deleteById(id);
        log.info(USER_DELETED_LOG_MSG, id);

        userProducerService.sendDeleteUserMessage(id);
    }

}
