package com.binary.CarShow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorization ->
                        authorization.requestMatchers("/api/v1/car/createCar", "/api/v1/member/register").permitAll()
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

/*    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorization -> authorization.requestMatchers("/")
                .permitAll().anyRequest()
                .authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf->csrf.disable());
        return http.build();
    }*/
    //Approach 1 with User with default password encoder
   /* @Bean
    public UserDetailsService userDetailsService() {
        User admin = (User) User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1234")
                .build();

        var user = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("password1")
                .authorities("user")
                .build();

        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(admin, user);

        return userDetailsService;
    }
    */
    // Approach 2 with using NoOpPasswordEncode -> no hashing/encoding

   /* @Bean
    public UserDetailsService userDetailsService() {
        User admin = (User) User.withUsername("Admin")
                .username("admin")
                .password("1234")
                .build();

        var user = User.withUsername("user1")
                .username("user1")
                .password("password1")
                .authorities("user")
                .build();

        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(admin, user);

        return userDetailsService;
    }*/
    @Bean
    public PasswordEncoder noOpPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

 /*   @Bean
    public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }*/

}
