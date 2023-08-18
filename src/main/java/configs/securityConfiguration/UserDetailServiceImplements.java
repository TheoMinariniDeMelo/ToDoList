package configs.securityConfiguration;

import exceptions.NotFoundRequestException;
import jakarta.transaction.Transactional;
import models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import services.GetMethods.Get;

import java.util.InputMismatchException;
import java.util.Optional;


@Service
@Transactional
public class UserDetailServiceImplements implements UserDetailsService {
    @Autowired
    Get get;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.of(get.findByEmail(email)).orElseThrow(() -> new InputMismatchException("Key error"));
    }
}
