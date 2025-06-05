package com.hunglevi.server.service.impl;

import com.hunglevi.server.entities.Users;
import com.hunglevi.server.repository.RolesRepository;
import com.hunglevi.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String login = (String) attributes.get("login");
        String email = (String) attributes.get("email");
//        String avatar = (String) attributes.get("avatar_url");

        // Nếu user chưa tồn tại, thì tạo mới
        userRepository.findByUsername(login).orElseGet(() -> {
            Users user = new Users();
            user.setUsername(login);
//            user.setAvatar()
            user.setEmail(email != null ? email : login + "@github.com");
            user.setRole(rolesRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Role USER not found")));
            return userRepository.save(user);
        });

        return oAuth2User;
    }
}
