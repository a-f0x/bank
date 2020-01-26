package ru.f0xdev.mobile.dto


open class ResponseDTO(
        val success: Boolean
)

class SuccessResponseDTO<T>(val result: T) : ResponseDTO(true)

data class ErrorResponseDTO(val code: Int,
                            val message: String? = null,
                            val details: Map<String, String> = emptyMap()) : ResponseDTO(false)

