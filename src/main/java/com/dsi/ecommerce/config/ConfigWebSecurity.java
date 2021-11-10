package com.dsi.ecommerce.config;


import com.dsi.ecommerce.service.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity

public class ConfigWebSecurity  extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetail userDetail;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws  Exception{
        //////
        auth.userDetailsService(userDetail);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected  void configure(HttpSecurity https) throws Exception{

    }

}
