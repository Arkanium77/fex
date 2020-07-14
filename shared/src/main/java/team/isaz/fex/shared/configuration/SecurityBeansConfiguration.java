package team.isaz.fex.shared.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import team.isaz.fex.shared.properties.SecureProperties;

@Configuration
@Slf4j
public class SecurityBeansConfiguration {
    private final SecureProperties properties;
    private final PasswordEncoder passwordEncoder;

    public SecurityBeansConfiguration(SecureProperties properties, PasswordEncoder passwordEncoder) {
        this.properties = properties;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationProvider basicProvider() {
        log.info("Client user: {}, password: {}", properties.getBasicProperties().getUsername(), properties.getBasicProperties().getPassword());
        UserDetails user = User
                .withUsername(properties.getBasicProperties().getUsername())
                .password(properties.getBasicProperties().getPassword())
                .roles("USER").build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(user);

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(manager);
        return provider;
    }

}
