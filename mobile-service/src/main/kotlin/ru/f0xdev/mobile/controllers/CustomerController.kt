package ru.f0xdev.mobile.controllers

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.f0xdev.common.mapToResponse
import ru.f0xdev.mobile.services.ICustomerService
import java.security.Principal

@RequestMapping("/mobile/api/v1/customer")
@RestController

class CustomerController(
        @Qualifier("stub_customer_service")
        private val customerService: ICustomerService) {


    @PreAuthorize("#oauth2.hasScope('mobil3e') and #oauth2.hasScope('read3')")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCustomer(principal: Principal): ResponseEntity<*> {
        return customerService.getCustomer(13).mapToResponse()
    }
}