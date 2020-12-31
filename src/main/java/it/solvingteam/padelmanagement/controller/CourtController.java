package it.solvingteam.padelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.dto.message.court.InsertCourtDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.service.CourtService;
import it.solvingteam.padelmanagement.validators.CourtValidator;
import it.solvingteam.padelmanagement.validators.CourtValidatorUpdate;

@RestController
@RequestMapping("court")
public class CourtController {

	@Autowired
	CourtService courtService;

	@Autowired
	CourtValidator courtValidator;
	
	@Autowired
	CourtValidatorUpdate courtValidatorUpdate;

	@PostMapping("insertCourt")
	public ResponseEntity<CourtDto> insertCourt(@Valid @RequestBody InsertCourtDto insertCourtDto,
			BindingResult bindingResult) throws Exception {
		courtValidator.validate(insertCourtDto, bindingResult);
		if (bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		CourtDto courtDto = courtService.insert(insertCourtDto);
		return ResponseEntity.status(HttpStatus.OK).body(courtDto);
	}

	@GetMapping("listAll/{idAdmin}")
	public ResponseEntity<List<CourtDto>> list(@PathVariable String idAdmin) throws Exception { 
		List<CourtDto> court = courtService.findAll(idAdmin);
		return ResponseEntity.status(HttpStatus.OK).body(court);
	}

	@GetMapping("show/{id}")
	public ResponseEntity<CourtDto> show(@PathVariable("id") String id) throws Exception, BindingResultException {
		return ResponseEntity.status(HttpStatus.OK).body(courtService.findById(id));

	}
	
	@PutMapping("update")
	public ResponseEntity<CourtDto> update(@Valid @RequestBody CourtDto courtDto, BindingResult bindingResult) throws Exception {
		courtValidatorUpdate.validate(courtDto, bindingResult);
		if (bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		CourtDto courtDtoUpdate = courtService.update(courtDto);
		return ResponseEntity.status(HttpStatus.OK).body(courtDtoUpdate);
	}
	
	@DeleteMapping("delete/{id}")
    public ResponseEntity<SuccessMessageDto> deleteCourt(@PathVariable String id) throws Exception{

        return ResponseEntity.status(HttpStatus.OK).body(courtService.setStatus(id));
	}
}
