package ru.f0xdev.mobile.services

import NOT_FOUND
import arrow.core.Either
import org.springframework.stereotype.Service
import ru.f0xdev.mobile.dto.CustomerDTO
import ru.f0xdev.mobile.dto.ErrorDTO

interface ICustomerService {
    fun getCustomer(customerId: Long): Either<ErrorDTO, CustomerDTO>
}

@Service("stub_customer_service")
class CustomerServiceStub : ICustomerService {
    override fun getCustomer(customerId: Long): Either<ErrorDTO, CustomerDTO> {
        return if (customerId > 10)
            Either.right(CustomerDTO(customerId, "79236177070", "Andrey", "Dzhigirey"))
        else
            Either.left(ErrorDTO(
                    code = NOT_FOUND,
                    message = "Customer not found"
            ))

    }
}

@Service("real_customer_service")
class CustomerServiceReal : ICustomerService {
    override fun getCustomer(customerId: Long): Either<ErrorDTO, CustomerDTO> {
        return Either.left(
                ErrorDTO(
                        code = 500,
                        message = "Not implemented",
                        parentThrowable = NotImplementedError("real_customer_service is not implemented")
                )
        )
    }
}



