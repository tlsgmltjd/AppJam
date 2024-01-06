package com.example.appjam.service;

import com.example.appjam.controller.dto.request.LoginRequest;
import com.example.appjam.controller.dto.response.UserInfoResponse;
import com.example.appjam.domain.UserEntity;
import com.example.appjam.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;

    @Transactional
    public void login(LoginRequest request) {
        if (!userJpaRepository.existsByToken(request.getToken())) {
            UserEntity user = UserEntity.builder()
                    .userName(request.getUserName())
                    .point(0)
                    .token(request.getToken())
                    .build();

            userJpaRepository.save(user);
        }
    }

    @Transactional
    public UserInfoResponse userInfo(String token) {
        UserEntity user = userJpaRepository.findByToken(token)
                .orElseThrow(RuntimeException::new);

        return UserInfoResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .point(user.getPoint())
                .token(user.getToken())
                .build();
    }

    @Transactional
    public void point(String token) {
        UserEntity user = userJpaRepository.findByToken(token)
                .orElseThrow(RuntimeException::new);
        user.pointUp();
    }
}
