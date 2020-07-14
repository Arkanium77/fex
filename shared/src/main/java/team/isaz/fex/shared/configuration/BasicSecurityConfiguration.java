package team.isaz.fex.shared.configuration;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import java.util.List;

@Configuration
public class BasicSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String ROOT_PATTERN = "/secured/basic/**";
    private static final List<String> publicUrls = Lists
            .newArrayList(
                    "/public/**"/*,
                    "/v2/**",
                    "/api-docs",
                    "/actuator/**",
                    "/swagger-ui.html",
                    "/swagger-resources/**",
                    "/webjars/springfox-swagger-ui/**",
                    "/"*/
            );
    private final AuthenticationProvider basicProvider;

    public BasicSecurityConfiguration(AuthenticationProvider basicProvider) {
        this.basicProvider = basicProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatchers()
                .antMatchers(publicUrls.toArray(new String[0]))
                .antMatchers(ROOT_PATTERN)
                .and()
                .authorizeRequests()
                .requestMatchers(
                        new OrRequestMatcher(
                                publicUrls.stream()
                                        .map(AntPathRequestMatcher::new)
                                        .toArray(AntPathRequestMatcher[]::new))
                )
                .permitAll()
                .antMatchers(ROOT_PATTERN).authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(basicProvider);
    }
}
