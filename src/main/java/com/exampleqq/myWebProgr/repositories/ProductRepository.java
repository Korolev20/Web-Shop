package com.exampleqq.myWebProgr.repositories;

import com.exampleqq.myWebProgr.models.Exbarc;
import com.exampleqq.myWebProgr.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Exbarc, Integer> {

    Exbarc findByExbarBody(String exbarBody);
    @Query(nativeQuery = true, name = "PostDtos1")
    List<ProductDTO> calculateProductCost(@Param("exbarBody1") String exbarBody1, @Param("quantity1") BigDecimal quantity1, @Param("isDiscountProvided") String isDiscountProvided);


    @Query(value = "SELECT e.exbarBody, p.packName, p.packQuant, pp.packPrice, pp.packBonus, u.unitName " +
            "FROM Exbarc e " +
            "JOIN Pack p ON e.packId = p.packId " +
            "JOIN PackPrc pp ON p.packId = pp.packId" +
            "JOIN Unit u ON p.unitId = u.unit_id " +
            "WHERE pp.packPrice > :priceThreshold AND pp.packBonus > :bonusThreshold",nativeQuery = true)
    List<Object []> findSalesWithTotalPriceGreaterThan(@Param("priceThreshold") BigDecimal priceThreshold,@Param("bonusThreshold") BigDecimal bonusThreshold);
}
