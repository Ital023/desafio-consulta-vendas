package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
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

		minDate = stringDateToLocalDateMin(minDateString);
		maxDate = stringDateToLocalDateMax(maxDateString);

		return repository.searchReport(minDate, maxDate, name, pageable);
    }


	public List<SaleSummaryDTO> searchSummary(String minDateString, String maxDateString) {
		LocalDate minDate = null;
		LocalDate maxDate = null;

		minDate = stringDateToLocalDateMin(minDateString);
		maxDate = stringDateToLocalDateMax(maxDateString);


		return repository.searchSummary(minDate, maxDate);
	}

	private LocalDate stringDateToLocalDateMin(String minDateString){
		if(minDateString == null || minDateString.isEmpty()) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
            return today.minusYears(1L);
		}else{
			return LocalDate.parse(minDateString);
		}
	}

	private LocalDate stringDateToLocalDateMax(String maxDateString){
		if(maxDateString == null || maxDateString.isEmpty()){
			return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}else{
			return LocalDate.parse(maxDateString);
		}
	}


}
