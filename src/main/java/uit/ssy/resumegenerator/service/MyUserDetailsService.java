package uit.ssy.resumegenerator.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uit.ssy.resumegenerator.models.User;
import uit.ssy.resumegenerator.models.UserProfile;
import uit.ssy.resumegenerator.registration.token.ConfirmationToken;
import uit.ssy.resumegenerator.registration.token.ConfirmationTokenService;
import uit.ssy.resumegenerator.repository.UserProfileRepository;
import uit.ssy.resumegenerator.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Author : Min Myat Thu Kha
 * Created At : 15/01/2025, Jan , 13:28
 * Project Name : ResumeGenerator
 **/
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);
        userOptional.orElseThrow(() -> new UsernameNotFoundException("Not found : "+username));
        return userOptional.get();
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository.findByUserName(user.getUsername()).isPresent();

        if (userExists) {
            throw new IllegalStateException("Username already taken");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        UserProfile userProfile = new UserProfile();
        userProfile.setUserName(user.getUsername());
        userProfile.setTheme(1);
        userProfile.setEmail(user.getEmail());
        userProfileRepository.save(userProfile);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public void enableUser(String userName) {
        userRepository.enableAppUser(userName);
    }

}
