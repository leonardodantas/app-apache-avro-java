package com.application.java.controllers;

import com.application.java.services.SendProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("send/product")
public class SendProductWithAvroController {

    private final SendProduct sendProduct;

    public SendProductWithAvroController(final SendProduct sendProduct) {
        this.sendProduct = sendProduct;
    }

    @PostMapping(path = "avro")
    @ResponseStatus(HttpStatus.OK)
    public void executeAvro(){
        log.info("-----------------------------------------");
        log.info("Initialized send product with apache avro");
        sendProduct.withAvro();
    }


    @PostMapping(path = "object/mapper")
    @ResponseStatus(HttpStatus.OK)
    public void executeString(){
        log.info("-----------------------------------------");
        log.info("Initialized send product with object mapper");
        sendProduct.withObjectMapper();
    }
}
