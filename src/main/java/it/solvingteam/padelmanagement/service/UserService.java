package it.solvingteam.padelmanagement.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.UserDto;
import it.solvingteam.padelmanagement.dto.message.user.InsertUserDto;
import it.solvingteam.padelmanagement.dto.message.user.UpdateUserDto;
import it.solvingteam.padelmanagement.mapper.user.UserMapper;
import it.solvingteam.padelmanagement.model.user.Role;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
//	    @Autowired
//	    private PasswordEncoder passwordEncoder;
	@Autowired
	private UserMapper userMapper;

	public Optional<User> findUserByUSername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	public UserDto findByIdUser(String id) throws Exception {
		if (!StringUtils.isNumeric(id)) {
			throw new Exception("id non valido");
		}
		User user = userRepository.findById(Long.parseLong(id)).get();
		if (user == null) {
			throw new Exception("id inesistente");
		}
		return userMapper.convertEntityToDto(user);
	}

	public User updateRole(User user) {
		user = this.userRepository.save(user);
		return user;
	}

	public UserDto signup(InsertUserDto insertUserDto) {

		// String passwordEncoded = passwordEncoder.encode(insertUserDto.getPassword());
		User user = userMapper.convertDtoToEntityInsert(insertUserDto);
		// user.setPassword(passwordEncoded);

		user.setRole(Role.ROLE_GUEST);

		this.userRepository.save(user);
		return userMapper.convertEntityToDto(user);

	}

//	public UserDto signIn(String username, String password) throws Exception {
//		UserDto userDto = userMapper.convertEntityToDto(userRepository.findByUsernameAndPassword(username, password));
//		if (userDto == null) {
//			throw new Exception("credenziali errate");
//		}
//		return userDto;
//	}

	public UserDto update(UpdateUserDto updateUserDto) throws Exception {
		User user = userMapper.convertDtoToEntityUpdate(updateUserDto);
		return userMapper.convertEntityToDto(userRepository.save(user));
	}

}
