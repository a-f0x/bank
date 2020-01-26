package ru.f0xdev.auth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
open class AuthBeanConfig {

    @Bean
    open fun passwordEncode(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
//
//    @Bean
//    @Autowired
//    open fun tokenStore(dataSource: DataSource): TokenStore {
//        val store = JdbcTokenStore(dataSource)
//        store.setAuthenticationKeyGenerator(
//                object : DefaultAuthenticationKeyGenerator() {
//                    override fun generateKey(values: MutableMap<String, String>?): String {
//                        return UUID.randomUUID().toString()
//                    }
//                }
//        )
//        return store
//    }
}