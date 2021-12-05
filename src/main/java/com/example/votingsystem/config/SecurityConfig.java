package com.example.votingsystem.config;

import com.example.votingsystem.model.Permission;
import com.example.votingsystem.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/*
Eugene Suleimanov Spring Security
https://youtu.be/7uxROJ1nduk
*/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() /* defence from csrf attacks disabled */
                .authorizeRequests() /* http requests must be authorized */
                    .antMatchers("/").hasAuthority(Permission.VOTE_API_MAIN.getPermission()) /* all types of requests to "/" base url are opened for Authority "Permission.VOTE_API_MAIN"*/
                    .antMatchers("/allVotes/**").hasAnyAuthority(Permission.VOTE_READ.getPermission(), Permission.VOTE_WRITE.getPermission()) /* all types of requests to "/allVotes" should have user Authority "Permission.VOTE_READ/WRITE" */
                    .antMatchers("/users/**").hasAnyAuthority(Permission.USER_READ.getPermission(), Permission.USER_WRITE.getPermission())
                    .anyRequest() /* every request..*/
                    .authenticated() /* .. must be authenticated */
                .and()
                    .httpBasic(); /* use standart http Basic encoder to encode user password from Login page */
//                .and()
//                    .logout()
//                    .clearAuthentication(true)
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .deleteCookies("JSESSIONID")
//                    .invalidateHttpSession(true);
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/login?logout").permitAll();
    }

//    InMemoryUserDetailsManager works with Spring Security User that archived in memory
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("Admin")
                        .password(passwordEncoder().encode("Admin"))
                        .roles(Role.ADMIN.name())
                        .authorities(Role.ADMIN.authorities())
                        .build(),
                User.builder()
                        .username("Lakomka")
                        .password(passwordEncoder().encode("Lakomka"))
                        .roles(Role.USER.name())
                        .authorities(Role.USER.authorities())
                        .build(),
                User.builder()
                        .username("Moderator")
                        .password(passwordEncoder().encode("Moderator"))
                        .roles(Role.MODERATOR.name())
                        .authorities(Role.MODERATOR.authorities())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
