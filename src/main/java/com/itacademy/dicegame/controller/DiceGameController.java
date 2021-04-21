package com.itacademy.dicegame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itacademy.dicegame.domain.DiceGame;
import com.itacademy.dicegame.service.DiceGameService;

@RestController
@RequestMapping("/players/{id}")
public class DiceGameController {
	/*
	 * Controller CRUD dice plays/games
	 */
	
	@Autowired
	DiceGameService diceGameService;
	
	//add new throw the dices
	@PostMapping("/games")
	@ResponseStatus(HttpStatus.CREATED)  // 201
	public DiceGame throwTheDices(@PathVariable("id") int idPlayer) {
		return diceGameService.newGame(idPlayer);
	}
	
	//TODO delete all player's games
	@DeleteMapping("/games")
	@ResponseStatus(HttpStatus.ACCEPTED)  // 202
	public String deleteAllGames() {
		return null;
	}
	
	//TODO get all player's games
	@GetMapping("/games")
	@ResponseStatus(HttpStatus.OK)  // 200
	public List<DiceGame> getAllGames() {
		return null;
	}
	
}