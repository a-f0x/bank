package ru.f0xdev.auth.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import ru.f0xdev.common.config.CustomTokenEnhancer


@Configuration
@EnableAuthorizationServer
open class OAuth2AuthorizationServerConfig(
        private val appConfig: AppConfig) : AuthorizationServerConfigurerAdapter() {

    @Autowired
    private val env: Environment? = null

    @Autowired
    @Qualifier("authenticationManagerBean")
    private val authenticationManager: AuthenticationManager? = null


    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        val tokenEnhancerChain = TokenEnhancerChain()
        tokenEnhancerChain.setTokenEnhancers(
                listOf(tokenEnhancer(), accessTokenConverter()))

        endpoints.tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(authenticationManager)
    }

    @Bean
    open fun tokenStore(): TokenStore? {
        return JwtTokenStore(accessTokenConverter())
    }

    @Bean
    open fun accessTokenConverter(): JwtAccessTokenConverter? {
        val converter = JwtAccessTokenConverter()
        converter.setSigningKey("123")
        return converter
    }

    @Bean
    @Primary
    open fun tokenServices(): DefaultTokenServices? {
        val defaultTokenServices = DefaultTokenServices()
        defaultTokenServices.setTokenStore(tokenStore())
        defaultTokenServices.setSupportRefreshToken(true)
        return defaultTokenServices
    }

    @Bean
    open fun tokenEnhancer(): TokenEnhancer? {
        return CustomTokenEnhancer()
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory().apply {
            appConfig.resourceServers.forEach { resourceServerConfig ->
                withClient(resourceServerConfig.clientId)
                        .secret(passwordEncoder().encode(resourceServerConfig.clientSecret))
                        .authorizedGrantTypes(*resourceServerConfig.grantTypes)
                        .scopes(*resourceServerConfig.scopes)
                        .accessTokenValiditySeconds(resourceServerConfig.accessTokenLiveTimeSec)
                        .refreshTokenValiditySeconds(resourceServerConfig.refreshTokenLiveTimeSec)
            }
        }
    }
}