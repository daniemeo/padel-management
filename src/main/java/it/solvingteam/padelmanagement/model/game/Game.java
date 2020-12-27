package it.solvingteam.padelmanagement.model.game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import it.solvingteam.padelmanagement.model.court.Court;
import it.solvingteam.padelmanagement.model.player.Player;
import it.solvingteam.padelmanagement.model.slot.Slot;

@Entity
public class Game {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate date;
	private Boolean payed;
	private Integer missingPlayers;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "court_id", nullable = false)
	private Court court;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "player_id", nullable = false)
	private Player player; 
	
	
	@ManyToMany
	@JoinTable(name = "game_player", joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "player_id", referencedColumnName = "ID"))
	private List<Player> players = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "game_slot", joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "slot_id", referencedColumnName = "ID"))
	private List<Slot> slots = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Boolean getPayed() {
		return payed;
	}

	public void setPayed(Boolean payed) {
		this.payed = payed;
	}

	public Integer getMissingPlayers() {
		return missingPlayers;
	}

	public void setMissingPlayers(Integer missingPlayers) {
		this.missingPlayers = missingPlayers;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}



	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}
	
	
	
	
}
