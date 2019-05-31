package org.annoml.servant.SpringAnnoMLServant.security;

import org.annoml.servant.SpringAnnoMLServant.service.AuthorDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthorDetailsService authorDetailsService;
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    public static final String REGEX_ID = "?:([1-9][0-9]*)|[1-9]";

    @Autowired
    public SecurityConfig(AuthorDetailsService authorDetailsService) {
        this.authorDetailsService = authorDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/index.html", "/", "/static/**", "/favicon.ico").permitAll()

                .antMatchers(HttpMethod.GET, "/questions/{id:" + REGEX_ID + "}/answers", "/questions/{id:" +  REGEX_ID + "}", "/questions/{qid:" + REGEX_ID + "}/answers/{aid:" + REGEX_ID + "}").permitAll()
                .antMatchers(HttpMethod.POST, "/users/register").permitAll()
                .regexMatchers(HttpMethod.GET, "/questions", "/questions\\?filter=unanswered", "/questions\\?filter=unsolved").permitAll()

                .regexMatchers(HttpMethod.GET, "/questions\\?filter=answeredbyme").authenticated()
                .antMatchers(HttpMethod.GET, "/users/user").authenticated()
                .antMatchers(HttpMethod.POST, "/questions", "/questions/{id:" + REGEX_ID + "}/answers", "/questions/{qid:" + REGEX_ID + "}/answers/{aid:" + REGEX_ID + "}/setfav").authenticated()
                .antMatchers(HttpMethod.DELETE, "/questions/{id:" + REGEX_ID + "}", "/questions/{qid:" + REGEX_ID + "}/answers/{aid:" + REGEX_ID + "}").authenticated()

                .anyRequest().denyAll().and()
                .httpBasic().authenticationEntryPoint(new RestAuthEntryPoint()).and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));
        // @formatter:on
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authorDetailsService).passwordEncoder(PASSWORD_ENCODER);
    }


    private class RestAuthEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException
                authException) throws IOException {
            response.addHeader("WWW-Authenticate", " ");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
