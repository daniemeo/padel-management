package it.solvingteam.padelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.ClubDto;
import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.dto.message.court.InsertCourtDto;
import it.solvingteam.padelmanagement.mapper.club.GetClubMapper;
import it.solvingteam.padelmanagement.mapper.court.CourtMapper;
import it.solvingteam.padelmanagement.model.admin.Admin;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.court.Court;
import it.solvingteam.padelmanagement.repository.CourtRepository;

@Service
public class CourtService {
	@Autowired 
	private CourtRepository courtRepository;
	
	@Autowired
	private ClubService clubService;
	
	@Autowired 
	private CourtMapper courtMapper;
	
	@Autowired 
	GetClubMapper getClubMapper;
	
	public CourtDto insert(InsertCourtDto insertCourtDto) {
		Club club = clubService.findClubByAdmin(Long.parseLong(insertCourtDto.getAdminId()));
		Court court = courtMapper.convertDtoToEntity(insertCourtDto);
		court.setClub(club);
		court= this.courtRepository.save(court);
		 return courtMapper.convertEntityToDto(court);
	}
	
	public Court findById(Long id) {
		return  this.courtRepository.findById(id).get();

	}
	
	

}
