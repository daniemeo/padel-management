package it.solvingteam.padelmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.AdminDto;
import it.solvingteam.padelmanagement.mapper.admin.AdminMapper;
import it.solvingteam.padelmanagement.model.admin.Admin;
import it.solvingteam.padelmanagement.model.user.Role;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	AdminMapper adminMapper;
	
	@Autowired
	UserService userService;
	
	public Admin insertAdmin(User user) {
		user.setRole(Role.ROLE_ADMIN);
		user = userService.updateRole(user);
		Admin admin = new Admin();
		admin.setUser(user);
		return adminRepository.save(admin);
	}
	
	public List<AdminDto> findAll() {
		List<Admin> admin = this.adminRepository.findAll().stream().collect(Collectors.toList());
		return adminMapper.convertEntityToDto(admin);
	}
	
	public Admin findById(Long id) throws Exception{
		return  this.adminRepository.findById(id).orElse(null);

	}
	
	public void deleteAdmin(@NotNull String id) {
		Admin admin =  this.adminRepository.findById(Long.parseLong(id)).orElse(null);
		this.adminRepository.delete(admin);
	}
	
	
	
	
	
}
