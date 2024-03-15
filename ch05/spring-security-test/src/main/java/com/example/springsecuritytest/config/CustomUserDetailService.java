package com.example.springsecuritytest.config;

import com.example.springsecuritytest.model.AuthoritiesEntity;
import com.example.springsecuritytest.model.UserEntity;
import com.example.springsecuritytest.repository.AuthoritiesRepository;
import com.example.springsecuritytest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username);
        AuthoritiesEntity authoritiesEntity = authoritiesRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("No user with " + username + " exists in the system");
        }

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .disabled(!userEntity.isEnabled())
                .authorities(authoritiesEntity.getAuthority())
                .build();
    }
}
