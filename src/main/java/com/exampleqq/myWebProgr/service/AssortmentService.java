package com.exampleqq.myWebProgr.service;

import com.exampleqq.myWebProgr.dto.AssortmentDTO;
import com.exampleqq.myWebProgr.repositories.AssortmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssortmentService {
    private final AssortmentRepository assortmentRepository;

    public AssortmentService(AssortmentRepository assortmentRepository) {
        this.assortmentRepository = assortmentRepository;
    }

        public List<AssortmentDTO> calculateProductCost1 () {
            return assortmentRepository.FindAllWithDescriptionQuery();
        }
    }

