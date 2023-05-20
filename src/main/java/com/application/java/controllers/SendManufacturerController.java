package com.application.java.controllers;

import com.application.java.services.SendManufacturer;
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
@Tag(name = "Envio de fabricante", description = "Envio de fabricante para fila kafka")
@RestController
@RequestMapping("send/manufacturer")
public class SendManufacturerController {

    private final SendManufacturer sendManufacturer;

    public SendManufacturerController(final SendManufacturer sendManufacturer) {
        this.sendManufacturer = sendManufacturer;
    }

    @Operation(summary = "Envio de manufacturer com Apache Avro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")})
    @PostMapping(path = "avro")
    @ResponseStatus(HttpStatus.OK)
    public void executeAvro() {
        log.info("-----------------------------------------");
        log.info("Initialized send manufacturer with apache avro");
        sendManufacturer.withAvro();
    }

}
