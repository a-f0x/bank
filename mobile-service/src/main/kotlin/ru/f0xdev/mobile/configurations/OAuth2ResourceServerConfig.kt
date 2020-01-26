package ru.f0xdev.mobile.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.AccessTokenConverter
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore


@Configuration
@EnableResourceServer
open class OAuth2ResourceServerConfig(private val accessTokenConverter: AccessTokenConverter) : ResourceServerConfigurerAdapter() {

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
            accessTokenConverter = accessTokenConverter
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
}