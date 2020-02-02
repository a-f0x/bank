package ru.f0xdev.mobile.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.AccessTokenConverter
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import ru.f0xdev.common.config.CustomAccessTokenConverter


@Configuration
@EnableResourceServer
open class OAuth2ResourceServerConfig : ResourceServerConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests().anyRequest().permitAll()
    }

    override fun configure(config: ResourceServerSecurityConfigurer) {
        config.tokenServices(tokenServices())
    }


    @Bean
    open fun tokenStore(): TokenStore? {
        return JwtTokenStore(accessTokenConverter())
    }

    @Bean
    open fun accessTokenConverter(): JwtAccessTokenConverter? {
        return JwtAccessTokenConverter().apply {
            accessTokenConverter = customAccessTokenConverter()
            setSigningKey("123")
        }
    }

    @Bean
    @Primary
    open fun tokenServices(): DefaultTokenServices? {
        return DefaultTokenServices().apply {
            setTokenStore(tokenStore())
        }
    }

    @Bean
    open fun customAccessTokenConverter(): AccessTokenConverter = CustomAccessTokenConverter()

}