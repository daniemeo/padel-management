package it.solvingteam.padelmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.AdminDto;
import it.solvingteam.padelmanagement.mapper.admin.AdminMapper;
import it.solvingteam.padelmanagement.model.admin.Admin;
import it.solvingteam.padelmanagement.repository.AdminRepository;
import it.solvingteam.padelmanagement.util.Util;

@Service
public class AdminService {
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	AdminMapper adminMapper;
	
	
	public List<AdminDto> findAll() {
		List<Admin> admin = this.adminRepository.findAll().stream().collect(Collectors.toList());
		return adminMapper.convertEntityToDto(admin);
	}
	
	public AdminDto findById(@NotNull String id) throws Exception{
		
		if(!Util.isNumber(id)) {
			throw new Exception("l'id deve essere un numero");
		}
		Admin admin =  this.adminRepository.findById(Long.parseLong(id)).orElse(null);
		return adminMapper.convertEntityToDto(admin);

	}
	
	
	public void deleteAdmin(@NotNull String id) {
		Admin admin =  this.adminRepository.findById(Long.parseLong(id)).orElse(null);
		this.adminRepository.delete(admin);
	}
	
	
	
	
	
}
