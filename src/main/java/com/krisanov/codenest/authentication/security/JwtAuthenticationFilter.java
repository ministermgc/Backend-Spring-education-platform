package com.krisanov.codenest.authentication.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krisanov.codenest.common.dto.ResponseErrorDto;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * This filter is responsible for intercepting HTTP requests and
 * performing JWT Token based authentication.
 * It extends OncePerRequestFilter to ensure execution once per each request.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * An instance of the JwtService, used to manage all related JWT operations.
     */
    private final JwtService jwtService;

    /**
     * An instance of the UserDetailsService, used to load User Details by user name.
     */
    private final UserDetailsService userDetailsService;

    /**
     * This method is responsible for filtering each incoming HTTP request.
     * It extracts JWT token from the "Authorization" header, retrieves user details
     * and sets authentication in the SecurityContext if JWT token is valid.
     *
     * @param request     that is to be proceed.
     * @param response    serves the proceeded request.
     * @param filterChain the rest of the filter chain.
     * @throws ServletException in case of a general servlet exception.
     * @throws IOException      in case of I/O errors.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            final String token = authorizationHeader.substring(7);
            final String username = jwtService.extractUsername(token);
            final SecurityContext context = SecurityContextHolder.getContext();
            if (username != null && context.getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    context.setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException ex) {
            ResponseErrorDto error = ResponseErrorDto.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .errorMessage(List.of("Token is invalid"))
                    .build();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(new ObjectMapper().writeValueAsString(error));
        } catch (Exception ex) {
            filterChain.doFilter(request, response);
        }
    }
}
