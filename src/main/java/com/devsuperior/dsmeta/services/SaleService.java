package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.ReportTestDTO;
import com.devsuperior.dsmeta.dto.SummaryTestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SummaryTestDTO> summaryTest3(String dataMin, String dataMax) {

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate year = today.minusYears(1L);

		if (dataMin == null || dataMin.isEmpty() && dataMax == null || dataMax.isEmpty()){
			List<SummaryTestDTO> result = repository.summaryTest1(year,today);
			return result.stream().toList();
		} else {

			LocalDate minDate = LocalDate.parse(dataMin);
			LocalDate maxDate = LocalDate.parse(dataMax);

			List<SummaryTestDTO> result = repository.summaryTest1(minDate,maxDate);
			return result.stream().toList();

		}

	}

	public Page<ReportTestDTO> reportTest2(String dataMin, String dataMax, String name, Pageable pageable) {

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate year = today.minusYears(1L);

		if (dataMin == null || dataMin.isEmpty() && dataMax == null || dataMax.isEmpty()){
			Page<ReportTestDTO> result = repository.reportTest2(year,today, name, pageable);
			return result;
		} else {

			LocalDate minDate = LocalDate.parse(dataMin);
			LocalDate maxDate = LocalDate.parse(dataMax);

			Page<ReportTestDTO> result = repository.reportTest2(minDate,maxDate, name, pageable);
			return result;
		}

	}
}

