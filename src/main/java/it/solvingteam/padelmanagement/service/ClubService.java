package it.solvingteam.padelmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.ClubDto;
import it.solvingteam.padelmanagement.mapper.club.ClubMapper;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.repository.ClubRepository;
import it.solvingteam.padelmanagement.util.Util;

@Service
public class ClubService {
	@Autowired
	ClubRepository clubRepository;
	
	@Autowired
	ClubMapper clubMapper;
	
	public List<ClubDto> findAll() {
		List<Club> clubs = this.clubRepository.findAll().stream().collect(Collectors.toList());
		return clubMapper.convertEntityToDto(clubs);
	}
	
	public ClubDto findById(@NotNull String id) throws Exception{
		
		if(!Util.isNumber(id)) {
			throw new Exception("l'id deve essere un numero");
		}
		Club club =  this.clubRepository.findById(Long.parseLong(id)).orElse(null);
		return clubMapper.convertEntityToDto(club);

	}
	

	public void deleteClub(@NotNull String id) {
		Club club =  this.clubRepository.findById(Long.parseLong(id)).orElse(null);
		this.clubRepository.delete(club);
	}
	

}
