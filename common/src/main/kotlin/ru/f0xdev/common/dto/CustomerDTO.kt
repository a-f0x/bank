package ru.f0xdev.common.dto

data class CustomerDTO(
        val id: Long,
        val phone: String? = null,
        val firstName: String? = null,
        val lastName: String? = null)