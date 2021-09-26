package com.mgsair.iotstack.mqttConnector.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {


    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        auth
            .inMemoryAuthentication()
            .withUser("user").password(encoder.encode("password")).roles("USER")
            .and()
            .withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
    }
}

class AppsecurityInitializer : AbstractSecurityWebApplicationInitializer(SecurityConfig::class.java) {

}