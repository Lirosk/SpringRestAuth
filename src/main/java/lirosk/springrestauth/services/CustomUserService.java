package lirosk.springrestauth.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lirosk.springrestauth.models.CustomUser;
import lirosk.springrestauth.repositories.CustomUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {
    private final CustomUserRepository customUserRepository;

    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = customUserRepository.findByUsername(username);
        if (customUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return customUser;
    }
}
