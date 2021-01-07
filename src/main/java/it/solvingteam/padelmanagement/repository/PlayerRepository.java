package it.solvingteam.padelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.padelmanagement.model.player.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{
	@Query("Select p from Player p join fetch p.club c  where p.id = ?1")
	public Player findPlayerClub(Long id);
	
	public Player findPlayerByUser_username(String username);
}
