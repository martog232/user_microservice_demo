package com.example.user_microservice_demo.service.user;

import com.example.user_microservice_demo.data.entity.Country;
import com.example.user_microservice_demo.data.entity.User;
import com.example.user_microservice_demo.data.repository.CountryRepository;
import com.example.user_microservice_demo.data.repository.UserRepository;
import com.example.user_microservice_demo.mappers.UserMapper;
import com.example.user_microservice_demo.web.model.UserReqModel;
import com.example.user_microservice_demo.web.model.UserSimpleRespModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User findById(Long id) throws EntityNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User with id %d not found".formatted(id));
        }
        return optionalUser.get();
    }

    @Override
    public List<UserSimpleRespModel> findAll() {
        return userRepository.findAll().stream().map(userMapper::mapToUserSimpleRespModel).toList();
    }

    @Override
    public List<UserSimpleRespModel> findByName(String name) {
        return userRepository.findAllByName(name)
                .stream()
                .map(userMapper::mapToUserSimpleRespModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserSimpleRespModel> findByCountry(String term) {
        return userRepository.findAllByCountryTerm(term)
                .stream()
                .map(userMapper::mapToUserSimpleRespModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserSimpleRespModel createUser(UserReqModel user) {
        Country country = countryRepository.findByCode(user.countryCode()).orElse(null);

        if (country == null) {
            throw new IllegalArgumentException("Country with code %s does not exist".formatted(user.countryCode()));
        }

        User buildedUser = User.builder()
                .name(user.name())
                .country(country)
                .build();

        userRepository.save(buildedUser);

        return UserSimpleRespModel.builder().name(user.name()).countryName(country.getName()).build();

    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
