package com.example.hello.service;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hello.dto.AuthRequest;
import com.example.hello.dto.AuthResponse;
import com.example.hello.dto.UserRequest;
import com.example.hello.dto.UserResponse;
import com.example.hello.entity.User;
import com.example.hello.exception.ApiException;
import com.example.hello.repository.UserRepository;
import com.example.hello.security.JwtUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UeserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    String googleClientId = System.getProperty("GOOGLE_CLIENT_ID");

    @Override
    public String createUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new ApiException(HttpStatus.CONFLICT, "Email already exists");
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail()); // Có thể thêm field này từ request
        user.setProvider("local"); // Nếu bạn có field để đánh dấu login từ local
        user.setAvatar_url("default-avatar.png"); // Mặc định avatar
        userRepository.save(user);

        return "User created successfully";

    }

    @Override
    public User updateUser(Long id, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public boolean deleteUser(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public Optional<User> getUserById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        // Chuyển đổi từ List<User> → List<UserResponse>
        List<UserResponse> responseList = users.stream().map(
                user -> new UserResponse(user.getUsername(), user.getEmail(), user.getStatus(), user.getAvatar_url()))
                .collect(Collectors.toList());

        return responseList;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

    @Override
    public AuthResponse login(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email, password));
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        final String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse loginWithGoogle(String googleToken) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance())
                .setAudience(Collections
                        .singletonList(googleClientId)) // Lấy từ biến môi trường
                .build();
        GoogleIdToken googleIdToken = null;
        try {
            googleIdToken = verifier.verify(googleToken);
        } catch (GeneralSecurityException | java.io.IOException e) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Google token verification failed");
        }
        if (googleIdToken == null) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid ID token");
        }

        Payload payload = googleIdToken.getPayload();
        String email = payload.getEmail();
        String name = (String) payload.get("name");

        // Kiểm tra user trong DB → nếu chưa có thì tạo mới
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            user = new User();
            user.setEmail(email);
            user.setUsername(name); // Lấy từ payload Google
            user.setProvider("google"); // Nếu bạn có field để đánh dấu login từ Google
            user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString())); // Tạo password ngẫu nhiên
            user.setAvatar_url("default-avatar.png"); // Mặc định avatar
            userRepository.save(user);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        // Tạo JWT nội bộ trả về
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }

    @Override
    public boolean existsByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByUsername'");
    }

    @Override
    public boolean existsByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByEmail'");
    }

}
