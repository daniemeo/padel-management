package it.solvingteam.padelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.court.Court;

public interface CourtRepository  extends JpaRepository<Court, Long> {
 public List<Court> findAllCourtByClub_id(Long id);
 
//	@Query("from Court c join fetch c.games g where g.id =?1")
//	public Court findCourtByGame(Long id);
 
public Court findCourtByGames_id(Long id);
}
