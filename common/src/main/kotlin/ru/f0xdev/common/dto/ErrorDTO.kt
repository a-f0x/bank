package ru.f0xdev.common.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ErrorDTO(val code: Int,
                    val message: String? = null,
                    val details: Map<String, String> = emptyMap(),
                    val parentThrowable: Throwable? = null)