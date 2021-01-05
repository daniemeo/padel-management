package it.solvingteam.padelmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.padelmanagement.model.game.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
	
	@Query("from Game g right join fetch g.player p where g.date =?1 and p.club.id =?2")
	public List<Game> listAllGamesSearch(LocalDate date,Long id);
	
	public  List<Game> findAllGamesByPlayer_Id(Long id);
	
	//per filtrare call for action(partite) aperte e con giocatori mancanti appartenenti al mio circolo 
	public List<Game> findAllGamesByPlayer_IdNotAndDateAfterAndMissingPlayersNotAndPlayer_Club_IdEquals(Long id, LocalDate date, Integer missingPlayers, Long ClubId);

}
