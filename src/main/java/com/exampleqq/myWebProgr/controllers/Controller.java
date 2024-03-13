package com.exampleqq.myWebProgr.controllers;

import com.exampleqq.myWebProgr.dto.SalesRequestDTO;
import com.exampleqq.myWebProgr.dto.AssortmentDTO;
import com.exampleqq.myWebProgr.dto.ProductDTO;
import com.exampleqq.myWebProgr.exceptions.ErrorResponse;
import com.exampleqq.myWebProgr.exceptions.NotCreatedException;
import com.exampleqq.myWebProgr.exceptions.NotFoundException;
import com.exampleqq.myWebProgr.models.Pack;
import com.exampleqq.myWebProgr.models.PackPrc;
import com.exampleqq.myWebProgr.models.Sales;
import com.exampleqq.myWebProgr.service.AssortmentService;

import com.exampleqq.myWebProgr.service.ProductService;
import com.exampleqq.myWebProgr.service.SalesService;
import com.exampleqq.myWebProgr.utils.BuilderError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")

public class Controller {

    private final AssortmentService assortmentService;
    private final ProductService productService;


    private final SalesService salesService;
    private final BuilderError builderError;

    @Autowired
    public Controller(AssortmentService assortmentService, ProductService productService,SalesService salesService, BuilderError builderError) {
        this.assortmentService = assortmentService;
        this.productService = productService;
        this.salesService = salesService;
        this.builderError=builderError;
    }

    @GetMapping("/findAll")
    public List<AssortmentDTO> getAllAssortment() {
        return assortmentService.calculateProductCost1();
    }


    @GetMapping("/calculateProductPrice")
    public List<ProductDTO> calculateProductPrice(@RequestParam(value = "exbarBody1") String exbarBody1, @RequestParam(value = "quantity1") BigDecimal quantity1, @RequestParam(value = "isDiscountProvided") String isDiscountProvided) {
        return productService.calculateProductCost(exbarBody1, quantity1, isDiscountProvided);
    }

    @PostMapping("/sales")
    public ResponseEntity<String> sales(@RequestBody @Valid SalesRequestDTO salesRequestDTO, BindingResult bindingResult) {
        builderError.handleBindingErrors(bindingResult);
        BigDecimal bigDecimal = salesService.processSales(salesRequestDTO);

        int comparisonResult = bigDecimal.compareTo(BigDecimal.ZERO);
        String message;
        if (comparisonResult >= 0) {
             message = "Операция оплаты прошла успешно, сдача = " + bigDecimal;
        }
        else {
            message = "Операция не выполненна: задолженность = " + bigDecimal;
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @PostMapping("deleteByData/{localDateData}")
    public ResponseEntity<HttpStatus>  deleteByDataTime(@PathVariable("localDateData")  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDateTime localDateData) {
        salesService.deleteOne(localDateData);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/getSalesWithNeedPriceAndBonus")
    public List<Object []> getSalesWithTotalPriceAboveThresholdAndSalesTag(@RequestParam BigDecimal priceThreshold,@RequestParam BigDecimal bonusThreshold) {
        return productService.findSalesWithTotalPriceAboveThreshold(priceThreshold,bonusThreshold);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(NotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(NotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
