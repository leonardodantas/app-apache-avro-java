package com.application.java.controllers;

import com.application.java.services.SendProduct;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Envio de produtos", description = "Envio de produtos para fila kafka")
@RestController
@RequestMapping("send/product")
public class SendProductController {

    private final SendProduct sendProduct;

    public SendProductController(final SendProduct sendProduct) {
        this.sendProduct = sendProduct;
    }

    @Operation(summary = "Envio de produtos com Apache Avro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")})
    @PostMapping(path = "avro")
    @ResponseStatus(HttpStatus.OK)
    public void executeAvro() {
        log.info("-----------------------------------------");
        log.info("Initialized send product with apache avro");
        sendProduct.withAvro();
    }

    @Operation(summary = "Envio de produtos com Object Mapper")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")})
    @PostMapping(path = "object/mapper")
    @ResponseStatus(HttpStatus.OK)
    public void executeObjectMapper() {
        log.info("-----------------------------------------");
        log.info("Initialized send product with object mapper");
        sendProduct.withObjectMapper();
    }
}
