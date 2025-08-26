package tw.com.phctw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityWebConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // .requestMatchers(
                //     "/",
                //     "/login",
                //     "/register",
                //     "/forgot",
                //     "/checkUsername",
                //     "/WEB-INF/views/**"
                // ).permitAll()
                // .requestMatchers(
                //     "/search"
                // ).hasRole("ADMIN")
                // .anyRequest().authenticated()
                // -----------------------------------------------------
                // 使用舊的方式，因為使用 RequestMatchers 會自動使用到 MvcRequestMatcher 而報錯。
                .requestMatchers(
                    new AntPathRequestMatcher("/"),
                    new AntPathRequestMatcher("/login"),
                    new AntPathRequestMatcher("/register"),
                    new AntPathRequestMatcher("/forgot"),
                    new AntPathRequestMatcher("/checkUsername"),
                    new AntPathRequestMatcher("/WEB-INF/views/**")
                ).permitAll()
                .requestMatchers(
                    new AntPathRequestMatcher("/search")
                ).hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/?login", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logout")
                .permitAll()
            );
        return http.build();
    }
}
