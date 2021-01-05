package it.solvingteam.padelmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.GameDto;
import it.solvingteam.padelmanagement.service.PlayerService;

@RestController
@RequestMapping("player")
public class PlayerController {
	
	@Autowired
	PlayerService playerService;
	

 
}
