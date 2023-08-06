package com.training.trainingdemo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.trainingdemo.entity.AddressEntity;
import com.training.trainingdemo.entity.UserEntity;
import com.training.trainingdemo.exception.DataNotFoundException;
import com.training.trainingdemo.model.Address;
import com.training.trainingdemo.model.User;
import com.training.trainingdemo.repository.UserRepository;
import com.training.trainingdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public User createUser(User user) {
        List<AddressEntity> addressEntities = user.getAddressList().stream()
                .map(address -> objectMapper.convertValue(address, AddressEntity.class))
                .collect(Collectors.toList());
        UserEntity userEntity = UserEntity.builder()
                .emailId(user.getEmailId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNo(user.getPhoneNo())
                .addressEntityList(addressEntities).build();
        userEntity = userRepository.save(userEntity);
        user.setId(userEntity.getId());
        return user;
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("user not found"));
        System.out.println(userEntity);
        User user = objectMapper.convertValue(userEntity, User.class);
        if(Objects.nonNull( userEntity.getAddressEntityList()) && !CollectionUtils.isEmpty( userEntity.getAddressEntityList())) {
            List<Address> addressList = userEntity.getAddressEntityList().stream()
                    .map(addressEntity -> objectMapper.convertValue(addressEntity, Address.class))
                    .collect(Collectors.toList());
            user.setAddressList(addressList);
        }
        return user;
    }

    @Override
    public User updateUser(User updatedUser) {
        UserEntity userEntity = objectMapper.convertValue(updatedUser, UserEntity.class);
        List<AddressEntity> addressEntities = updatedUser.getAddressList().stream()
                .map(address -> objectMapper.convertValue(address, AddressEntity.class))
                .collect(Collectors.toList());
        userEntity.setAddressEntityList(addressEntities);
        userRepository.save(userEntity);
        return updatedUser;
    }

    @Override
    public String deleteUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("user not found"));
        userRepository.delete(userEntity);
        return "SUCCESS";
    }
}
