package ru.f0xdev.auth

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class AuthApplication : CommandLineRunner {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(AuthApplication::class.java, *args)
        }
    }

    override fun run(vararg args: String?) {

    }
}