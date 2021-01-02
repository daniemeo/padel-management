package it.solvingteam.padelmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.padelmanagement.model.game.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
	
	@Query("from Game g join fetch g.player p where g.date =?1 and p.club.id =?2")
	public List<Game> listAllGamesSearch(LocalDate date, Long id);

}
