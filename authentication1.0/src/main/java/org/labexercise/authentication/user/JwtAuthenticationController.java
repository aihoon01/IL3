package org.labexercise.authentication.user;

import lombok.Data;
import lombok.Getter;
import org.labexercise.authentication.user.service.MyUserDetailsService;
import org.labexercise.authentication.user.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public Map<String, String> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            Map<String, String> response = new HashMap<>();
            response.put("jwt", jwt);
            return response;

        } catch (AuthenticationException e) {
            throw new Exception("Invalid username or password", e);
        }
    }

    @GetMapping("/profile")
    public Map<String, Object> getProfile() {
        // Retrieve the currently authenticated user's name from SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Create response data for the profile
        Map<String, Object> profileData = new HashMap<>();
        profileData.put("username", username);
        profileData.put("message", "This is the protected profile data.");

        return profileData;
    }

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody RegisterRequest registerRequest ) {
        UserEntity registerDto = UserEntity.builder()
                .password(registerRequest.getPassword())
                .username(registerRequest.getUsername())
                .build();
        userDetailsService.registerNewUser(registerDto);
        Map<String, String> response = new HashMap<>();
        response.put("username", registerDto.getUsername());
        return response;
    }


}

@Getter
class AuthenticationRequest {
    private String username;
    private String password;
}

@Data
class RegisterRequest {
    private String username;
    private String password;
}
