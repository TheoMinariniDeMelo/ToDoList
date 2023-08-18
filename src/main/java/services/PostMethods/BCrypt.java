package services.PostMethods;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public interface BCrypt {
    static String Encoder(String passWord) {
        return new BCryptPasswordEncoder().encode(passWord);
    }

    ;
}
