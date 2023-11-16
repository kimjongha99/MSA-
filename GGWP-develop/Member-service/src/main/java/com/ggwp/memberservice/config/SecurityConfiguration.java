package com.ggwp.memberservice.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggwp.memberservice.JsonLoginProcessingFilter;
import com.ggwp.memberservice.jwt.JwtAuthenticationProcessingFilter;
import com.ggwp.memberservice.jwt.JwtService;
import com.ggwp.memberservice.login.LoginFailureHandler;
import com.ggwp.memberservice.login.LoginSuccessHandler;
import com.ggwp.memberservice.repository.MemberRepository;
import com.ggwp.memberservice.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfiguration {

    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    private final ObjectPostProcessor<Object> objectPostProcessor;

    @Autowired
    public SecurityConfiguration( ObjectMapper objectMapper, MemberRepository memberRepository, ObjectPostProcessor<Object> objectPostProcessor) {
        this.objectMapper = objectMapper;
        this.memberRepository = memberRepository;
        this.objectPostProcessor = objectPostProcessor;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/member/signup","/login")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .requestCache(RequestCacheConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        http.addFilterBefore(new JwtAuthenticationProcessingFilter(authenticationManager(), ?), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(loginProcessingFilter(authenticationManager(), authenticationSuccessHandler(?), authenticationFailureHandler()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AbstractAuthenticationProcessingFilter loginProcessingFilter(
            AuthenticationManager authenticationManager,
            AuthenticationSuccessHandler successHandler,
            AuthenticationFailureHandler failureHandler) {
        JsonLoginProcessingFilter jsonLoginProcessingFilter = new JsonLoginProcessingFilter(objectMapper);
        jsonLoginProcessingFilter.setAuthenticationManager(authenticationManager);
        jsonLoginProcessingFilter.setAuthenticationSuccessHandler(successHandler);
        jsonLoginProcessingFilter.setAuthenticationFailureHandler(failureHandler);
        return jsonLoginProcessingFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);

        // UserDetailsService, PasswordEncoder 설정
        builder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return builder.build();
    }



    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(memberRepository);
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new LoginFailureHandler(objectMapper);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}