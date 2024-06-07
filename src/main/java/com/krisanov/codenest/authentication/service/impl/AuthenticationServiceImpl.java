package com.krisanov.codenest.authentication.service.impl;

import com.krisanov.codenest.authentication.dto.AuthenticationRequestDto;
import com.krisanov.codenest.authentication.dto.AuthenticationResponseDto;
import com.krisanov.codenest.authentication.dto.RegistrationRequestDto;
import com.krisanov.codenest.authentication.mapper.RegistrationRequestDtoMapper;
import com.krisanov.codenest.authentication.security.JwtService;
import com.krisanov.codenest.authentication.service.AuthenticationService;
import com.krisanov.codenest.common.exception.NotFoundException;
import com.krisanov.codenest.domain.UserAccount;
import com.krisanov.codenest.repository.RoleRepository;
import com.krisanov.codenest.repository.UserAccountRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final RegistrationRequestDtoMapper registrationRequestDtoMapper;

    private final UserAccountRepository userAccountRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDTO,
                                                  HttpServletResponse response) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequestDTO.username(),
                authenticationRequestDTO.password()));

        UserAccount userAccount = userAccountRepository
                .findByUsername(authenticationRequestDTO.username())
                .orElseThrow(() -> new NotFoundException("User not found"));
        String accessToken = jwtService.generateAccessToken(userAccount);
        String refreshToken = jwtService.generateRefreshToken(userAccount);
        return AuthenticationResponseDto.builder()
                .userId(userAccount.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponseDto register(RegistrationRequestDto registrationRequestDTO) {
        UserAccount userAccount = registrationRequestDtoMapper.map(registrationRequestDTO);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccount.setAuthorities(Set.of(roleRepository
                .findByAuthority("ROLE_USER")
                .orElseThrow(() -> new NotFoundException("Role was not found"))));
        UserAccount savedUserAccount = userAccountRepository.save(userAccount);

        String accessToken = jwtService.generateAccessToken(savedUserAccount);
        String refreshToken = jwtService.generateRefreshToken(savedUserAccount);
        return AuthenticationResponseDto.builder()
                .userId(savedUserAccount.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponseDto refreshAccessToken(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(refreshToken, userDetails)) {
            return AuthenticationResponseDto.builder()
                    .accessToken(jwtService.generateAccessToken(userDetails))
                    .refreshToken(refreshToken)
                    .build();
        }

        throw new BadCredentialsException("Refresh token is not valid");
    }
}
