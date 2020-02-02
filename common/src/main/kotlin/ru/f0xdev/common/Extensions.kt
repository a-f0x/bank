package ru.f0xdev.common

import arrow.core.Either
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import ru.f0xdev.common.dto.ErrorDTO
import ru.f0xdev.common.dto.ErrorResponseDTO
import ru.f0xdev.common.dto.ResponseDTO
import ru.f0xdev.common.dto.SuccessResponseDTO

const val NOT_FOUND = 404
const val SERVER_ERROR = 500

val httpStatuses = mapOf(
        NOT_FOUND to HttpStatus.UNAUTHORIZED,
        SERVER_ERROR to HttpStatus.INTERNAL_SERVER_ERROR
)

fun Map<Int, HttpStatus>.getOr500(key: Int): HttpStatus = getOrDefault(key, HttpStatus.INTERNAL_SERVER_ERROR)

fun ErrorDTO.toResponseEntity(): ResponseEntity<ErrorResponseDTO> = ResponseEntity.status(httpStatuses.getOr500(code)).body(ErrorResponseDTO(code, message, details))


fun Either<ErrorDTO, Any>.mapToResponse(): ResponseEntity<*> {
    return when (this) {
        is Either.Left -> a.toResponseEntity()

        is Either.Right -> {
            when (val result = b) {
                is Boolean -> {
                    ResponseEntity.ok(ResponseDTO(result))
                }
//                is List<*> -> {
////                    ResponseEntity.ok(ListResponse(b))
//                }
                else -> {
                    ResponseEntity.ok(SuccessResponseDTO(result))
                }
            }
        }
    }
}
