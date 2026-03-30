package co.istad.suiii.fswd.sbapp.security;

import co.istad.suiii.fswd.sbapp.repository.UserRepository;

import co.istad.suiii.fswd.sbapp.security.model.CustomUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @NullMarked
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername ={}", username);

        CustomUserDetail customUserDetail = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " ));
        log.info("CustomUserDetail : {}", customUserDetail);
        return customUserDetail;
    }
}
