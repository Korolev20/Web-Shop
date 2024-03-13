package com.exampleqq.myWebProgr.service;

import com.exampleqq.myWebProgr.dto.ProductDTO;
import com.exampleqq.myWebProgr.dto.SalesRequestDTO;

import com.exampleqq.myWebProgr.exceptions.NotFoundException;
import com.exampleqq.myWebProgr.models.Sales;
import com.exampleqq.myWebProgr.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesService {

    private final SalesRepository salesRepository;

    @Autowired
    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Transactional
    public BigDecimal processSales(SalesRequestDTO salesRequestDTO) {
        List<Sales> salesList = new ArrayList<>();
        List<BigDecimal> bigDecimalAll = colData(salesRequestDTO);

        BigDecimal bigDecimalResult = bigDecimalAll.get(0);
        BigDecimal bigDecimalMoneyOfCash = bigDecimalAll.get(1);
        BigDecimal bigDecimalMoneyOfCard = bigDecimalAll.get(2);

        int comparisonResult = bigDecimalResult.compareTo(BigDecimal.ZERO);
        int comparisonMoneyOfCash = bigDecimalMoneyOfCash.compareTo(BigDecimal.ZERO);
        int comparisonResultMoneyOfCard = bigDecimalMoneyOfCard.compareTo(BigDecimal.ZERO);

        if (comparisonResult >= 0) {

            for (ProductDTO productDTO : salesRequestDTO.getProductDTOList()) {
                Sales saleEntity = new Sales();
                saleEntity.setSalesTime(LocalDateTime.now());
                saleEntity.setSalesTag(BigDecimal.valueOf(0));
                saleEntity.setExbarBody(productDTO.getExbarBody());
                saleEntity.setPackName(productDTO.getPackName());
                saleEntity.setUnitName(productDTO.getUnitName());
                saleEntity.setPrice(productDTO.getTotalPrice());
                saleEntity.setBonusPrice(productDTO.getBonusPrice());
                saleEntity.setQuantity(productDTO.getQuantity1());
                salesList.add(salesRepository.save(saleEntity));

                if (comparisonMoneyOfCash == 1 && comparisonResultMoneyOfCard == 1) {

                    Sales existingSale = salesRepository.findById(1).orElse(null);
                    if (existingSale != null) {
                        existingSale.setPrice(existingSale.getPrice().add(saleEntity.getPrice()));
                        existingSale.setBonusPrice(existingSale.getBonusPrice().add(saleEntity.getBonusPrice()));
                        salesRepository.save(existingSale);
                    }
                } else if (comparisonMoneyOfCash == 1 && comparisonResultMoneyOfCard == 0) {
                    Sales existingSale = salesRepository.findById(2).orElse(null);
                    if (existingSale != null) {
                        existingSale.setPrice(existingSale.getPrice().add(saleEntity.getPrice()));
                        existingSale.setBonusPrice(existingSale.getBonusPrice().add(saleEntity.getBonusPrice()));
                        salesRepository.save(existingSale);
                    }
                } else if (comparisonMoneyOfCash == 0 && comparisonResultMoneyOfCard ==1) {
                    Sales existingSale = salesRepository.findById(3).orElse(null);
                    if (existingSale != null) {
                        existingSale.setPrice(existingSale.getPrice().add(saleEntity.getPrice()));
                        existingSale.setBonusPrice(existingSale.getBonusPrice().add(saleEntity.getBonusPrice()));
                        salesRepository.save(existingSale);
                    }
                };
            }


        } else {
            return bigDecimalResult;
        }
        return bigDecimalResult;
    }

    @Transactional
    public void deleteOne(LocalDateTime localDateTime) {
        Optional<Sales> existingSales = salesRepository.findBySalesTime(localDateTime);
        if (existingSales.isPresent()) {
            salesRepository.delete(existingSales.get());
        } else {
            throw new NotFoundException();
        }

    }


    public static List<BigDecimal> colData(SalesRequestDTO salesRequestDTO) {
        List<BigDecimal> listPriceOfAllProduct = new ArrayList<>();

        BigDecimal priceOneProduct;

        for (ProductDTO productDTO : salesRequestDTO.getProductDTOList()) {
            priceOneProduct = productDTO.getTotalPrice();
            listPriceOfAllProduct.add(priceOneProduct);

        }
        BigDecimal sumPriceOfAllProduct = listPriceOfAllProduct
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal moneyOfCard = salesRequestDTO.getCardPrice();
        BigDecimal moneyOfCash = salesRequestDTO.getCashPrice();
        BigDecimal totalPriceClient = moneyOfCard.add(moneyOfCash);

        BigDecimal okOrNot = totalPriceClient.subtract(sumPriceOfAllProduct);
        List<BigDecimal> dataBigDecimal = new ArrayList<>();
        dataBigDecimal.add(okOrNot);
        dataBigDecimal.add(moneyOfCash);
        dataBigDecimal.add(moneyOfCard);

        return dataBigDecimal;
    }



}

