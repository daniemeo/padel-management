package it.solvingteam.padelmanagement.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.UserDto;
import it.solvingteam.padelmanagement.dto.message.user.InsertUserDto;
import it.solvingteam.padelmanagement.mapper.user.UserMapper;
import it.solvingteam.padelmanagement.model.UserPrincipal;
import it.solvingteam.padelmanagement.model.user.Role;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new UserPrincipal(user);
	}

	public UserDto signup(InsertUserDto insertUserDto) {
		String username = insertUserDto.getUsername();
		String passwordEncoded = passwordEncoder.encode(insertUserDto.getPassword());

		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoded);
		user.setRole(Role.ROLE_GUEST);
		user.setName(insertUserDto.getName());
		user.setSurname(insertUserDto.getSurname());
		user.setDateOfBirth(LocalDate.parse(insertUserDto.getDateOfBirth()));
		user.setMobile(insertUserDto.getMobile());
		user.setMailAddress(insertUserDto.getMailAddress());
		user.setProfilePic(insertUserDto.getProfilePic());
		this.userRepository.save(user);
		return userMapper.convertEntityToDto(user);
	}

}