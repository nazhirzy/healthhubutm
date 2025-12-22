package  com.secj3303.security;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   /*  @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin1234")
                .roles("ADMIN", "MEMBER")
                .build();

        UserDetails member = User.withUsername("member")
                .password("{noop}member1234")
                .roles("MEMBER")
                .build();

        UserDetails trainer = User.withUsername("trainer")
                .password("{noop}trainer1234")
                .roles("TRAINER")
                .build();

        return new InMemoryUserDetailsManager(admin, member, trainer);
    }
    */
   // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {


    //     UserDetails admin = User.withUsername("admin")
    //             .password("{noop}admin123")
    //             .roles("ADMIN")
    //             .build();


    //     UserDetails user = User.withUsername("user")
    //             .password("{noop}user123")
    //             .roles("USER")
    //             .build();
    //     return new InMemoryUserDetailsManager(admin, user);
    // }


    @Bean
    public JdbcUserDetailsManager userDetailsService(DataSource dataSource) {


        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");
        return manager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf().disable()

            .authorizeHttpRequests()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/member/**").hasRole("MEMBER")
                .requestMatchers("/trainer/**").hasRole("TRAINER")
                .anyRequest().authenticated()
                .and()

            .formLogin()
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
                .and()

            .logout()
                .logoutSuccessUrl("/login")
                .permitAll();

        return http.build();
    }
}
