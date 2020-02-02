package ru.f0xdev.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
class AppConfig {
    lateinit var resourceServers: List<ResourceServerConfig>
}


class ResourceServerConfig {
    lateinit var name: String
    lateinit var clientSecret: String
    lateinit var clientId: String
    lateinit var grantTypes: Array<String>
    var accessTokenLiveTimeSec: Int = 3600 // 1 hour
    var refreshTokenLiveTimeSec: Int = 2592000 // 30 days
    lateinit var scopes: Array<String>
}


