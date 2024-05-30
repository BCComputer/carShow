package com.binary.CarShow.config;

import com.binary.CarShow.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private MemberDetailsService memberDetailsService;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorization ->
                        authorization.requestMatchers( "/api/v1/member/register", "/api/v1/member/login").permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Cross-Site Request Forgery
                .csrf(csrf -> csrf.disable());
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(memberDetailsService)
                //.passwordEncoder(noOpPasswordEncoder())
                .passwordEncoder(noOpPasswordEncoder())
                .and().build();

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
    public PasswordEncoder noOpPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
  /*  @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }*/


 /*   @Bean
    public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }*/

}
