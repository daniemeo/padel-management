package it.solvingteam.padelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.model.player.Player;
import it.solvingteam.padelmanagement.model.user.Role;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.repository.PlayerRepository;

@Service
public class PlayerService {
	@Autowired 
	private PlayerRepository playerRepository;
	
	@Autowired
	private UserService userService;
	
	public Player insert(Player player ) {
		User user = userService.findById((player.getUser().getId()));
		user.setRole(Role.ROLE_PLAYER);
		user = userService.updateRole(user);
		player.setUser(user);
		return playerRepository.save(player);
	}
	
	public Player getPlayerClub(String id) {
		return playerRepository.findPlayerClub(Long.parseLong(id));
	}
	
	public Player findById(String id) throws Exception {
		
		return this.playerRepository.findById(Long.parseLong(id)).get();

	}
}
