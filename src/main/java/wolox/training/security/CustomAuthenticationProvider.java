package wolox.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    public CustomAuthenticationProvider() {}

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User new_user = new User();
        new_user.setBooks(new ArrayList<Book>());
        new_user.setName("facu");
        new_user.setUsername("facu.compe");
        new_user.setBirthdate(LocalDate.now());
        new_user.setPassword("password");
        userRepository.save(new_user);

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new BadCredentialsException("Invalid user or password");
        } else if (!user.validPassword(password)) {
            throw new BadCredentialsException("Invalid user or password");
        } else {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
