package uci.capstone.invictus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import uci.capstone.invictus.authentication.UserDetailsServiceImpl;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/invictus/v1/users/", "/invictus/v1/users");
    }

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    /*
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.logout().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/invictus/v1/groups/aggregator/illness/",
                        "/invictus/v1/groups/aggregator/location/",
                        "/invictus/v1/groups/aggregator/illness",
                        "/invictus/v1/groups/aggregator/location"
                        ).hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/invictus/v1/groups/",
                        "/invictus/v1/groups"
                ).hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/invictus/v1/groups/",
                        "/invictus/v1/groups"
                ).hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .cors().and()
                .httpBasic()
                .authenticationEntryPoint(authEntryPoint);
    }
}
