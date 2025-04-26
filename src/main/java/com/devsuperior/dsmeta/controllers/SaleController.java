package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportTestDTO;
import com.devsuperior.dsmeta.dto.SummaryTestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportTestDTO>> reportTest2(
			@RequestParam(name = "minDate", required = false)String minDate,
			@RequestParam(name = "maxDate", required = false)String maxDate,
			@RequestParam(name = "name", defaultValue = "")String name,
			Pageable pageable){

		Page<ReportTestDTO> result = service.reportTest2(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryTestDTO>> summaryTest1(
			@RequestParam(name = "minDate", required = false)String minDate,
			@RequestParam(name = "maxDate", required = false)String maxDate) {

		List<SummaryTestDTO> result = service.summaryTest3(minDate, maxDate);
		return ResponseEntity.ok(result);

	}

}
