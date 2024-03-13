package com.exampleqq.myWebProgr.repositories;

import com.exampleqq.myWebProgr.dto.AssortmentDTO;
import com.exampleqq.myWebProgr.models.Exbarc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssortmentRepository extends JpaRepository<Exbarc, Integer>{


    @Query(nativeQuery = true, name = "PostDtos")
    List<AssortmentDTO> FindAllWithDescriptionQuery();
}





