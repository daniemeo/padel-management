package it.solvingteam.padelmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.ClubDto;
import it.solvingteam.padelmanagement.mapper.club.ClubMapper;
import it.solvingteam.padelmanagement.mapper.club.GetClubMapper;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.repository.ClubRepository;

@Service
public class ClubService {
	@Autowired
	ClubRepository clubRepository;
	
	@Autowired
	ClubMapper clubMapper;
	
	@Autowired 
	GetClubMapper getClubMapper;
	
	public Club insertClub(Club club) {
		return clubRepository.save(club);
	}
	
	public List<ClubDto> findAll() {
		List<Club> clubs = this.clubRepository.findAll().stream().collect(Collectors.toList());
		return getClubMapper.convertEntityToDto(clubs);
	}
	
	public Club findById(Long id) {
		
		return  this.clubRepository.findById(id).orElse(null);
		
	}
	

	public void deleteClub(@NotNull String id) {
		Club club =  this.clubRepository.findById(Long.parseLong(id)).orElse(null);
		this.clubRepository.delete(club);
	}
	

}
