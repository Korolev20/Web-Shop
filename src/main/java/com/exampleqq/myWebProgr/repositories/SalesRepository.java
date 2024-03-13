package com.exampleqq.myWebProgr.repositories;

import com.exampleqq.myWebProgr.models.Exbarc;
import com.exampleqq.myWebProgr.models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {

    Optional<Sales> findBySalesTime(LocalDateTime salesTime);


}
