package com.app.security.config;

import com.app.exception.GlobalHandlerException;
import com.app.security.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private BCryptPasswordEncoder bCryptPassEncoder;

    @Autowired
    public SecurityConfig(AppUserService appUserService, BCryptPasswordEncoder bCryptPassEncoder) {
        this.appUserService = appUserService;
        this.bCryptPassEncoder = bCryptPassEncoder;
    }

    public SecurityConfig(boolean disableDefaults, AppUserService appUserService, BCryptPasswordEncoder bCryptPassEncoder) {
        super();
        this.appUserService = appUserService;
        this.bCryptPassEncoder = bCryptPassEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/dentist/*")
                .hasAuthority("ADMIN")
                .antMatchers("/patient/*")
                .hasAuthority("ADMIN")
                .antMatchers("/address/*")
                .hasAuthority("ADMIN")
                .antMatchers("/shift/*")
                .hasAuthority("ADMIN")
                .and()
                .formLogin();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPassEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

}

