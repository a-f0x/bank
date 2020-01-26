package ru.f0xdev.mobile

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class MobileApplication : CommandLineRunner {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(MobileApplication::class.java, *args)
        }
    }

    override fun run(vararg args: String?) {

    }
}