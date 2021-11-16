package com.dsi.ecommerce.config;

import com.dsi.ecommerce.utility.constants.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class ConfigWebSecurity extends WebSecurityConfigurerAdapter {

        @Autowired
        private UserDetailsService userDetailsService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService);
        }

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity https) throws Exception {

                https.authorizeRequests()
                        .antMatchers("/admin").hasAnyAuthority(UserRoles.ADMIN.toString())
                        .antMatchers("/users/**").hasAnyAuthority(UserRoles.SELLER.toString(), UserRoles.BUYER.toString(),
                                UserRoles.ADMIN.toString())
                        .antMatchers("/my_shop", "/update_shop").hasAnyAuthority(UserRoles.SELLER.toString())
                        .antMatchers("/").permitAll()
                        .and().formLogin()
                        .loginPage("/login").permitAll().failureUrl("/login-failed")
                        .and().
                        logout().invalidateHttpSession(true)
                        .clearAuthentication(true).
                        logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/logout-success").permitAll();


        }

}
