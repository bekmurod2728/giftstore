package uz.giftstore.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserDetService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
