package ru.f0xdev.mobile.controllers

import mapToResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.f0xdev.mobile.services.ICustomerService

@RequestMapping("/api/v1/customer")
@RestController
class CustomerController(
        @Qualifier("stub_customer_service")
        private val customerService: ICustomerService) {


    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCustomer(): ResponseEntity<*> {
        return customerService.getCustomer(10).mapToResponse()
    }
}