package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
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

    public Page<SaleReportDTO> searchReport(String minDateString,
											String maxDateString,
											String name,
											Pageable pageable) {

		LocalDate minDate = null;
		LocalDate maxDate = null;

		if(minDateString == null || minDateString.isEmpty()) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			minDate = today.minusYears(1L);
		}else{
			minDate = LocalDate.parse(minDateString);
		}

		if(maxDateString == null || maxDateString.isEmpty()){
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}else{
			maxDate = LocalDate.parse(maxDateString);
		}

		Page<SaleReportDTO> result = repository.searchReport(minDate, maxDate, name, pageable);

		return result;
    }
}
