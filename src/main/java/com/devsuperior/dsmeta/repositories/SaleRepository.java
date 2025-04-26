package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportTestDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryTestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SummaryTestDTO(obj.seller.name, SUM(obj.amount)) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY obj.seller.name")
    List<SummaryTestDTO> summaryTest1(LocalDate minDate, LocalDate maxDate);

    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.ReportTestDTO(obj.id, obj.date, obj.amount, obj.seller.name) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate AND UPPER(obj.seller.name) LIKE CONCAT('%',UPPER(:name),'%')",
            countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller"  )
    Page<ReportTestDTO> reportTest2(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

}
