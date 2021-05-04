package com.itacademy.dicegame.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itacademy.dicegame.domain.diceGame.DiceGame;
import com.itacademy.dicegame.domain.player.Player;
import com.itacademy.dicegame.service.DiceGameService;

@RestController
@RequestMapping("/players")
public class DiceGameController {
	/*
	 * Controller CRUD dice plays/games
	 */
	
	@Autowired
	private DiceGameService diceGameService;
	
	public DiceGameController() {
	}
	
	//add new throw the dices
	@PostMapping("/{id}/games/{gameType}")
	@ResponseStatus(HttpStatus.CREATED)  // 201
	@PreAuthorize("hasAuthority('create:game')")
	public DiceGame throwTheDices(@PathVariable("id") UUID idPlayer, @PathVariable("gameType") String gameType) {
		return diceGameService.newGame(idPlayer, gameType);
	}
	
	//get all player's games
	@GetMapping("/{id}/games/{gameType}")
	@ResponseStatus(HttpStatus.OK)  // 200
	@PreAuthorize("hasAuthority('get:game')")
	public List<DiceGame> getAllGames(@PathVariable("id") UUID idPlayer,@PathVariable("gameType") String gameType) {
		return diceGameService.getAllGames(idPlayer, gameType);
	}
	
	//delete all player's games
	@DeleteMapping("/{id}/games/{gameType}")
	@ResponseStatus(HttpStatus.ACCEPTED)  // 202
	@PreAuthorize("hasAuthority('delete:game')")
	public String deleteAllGames(@PathVariable("id") UUID idPlayer,@PathVariable("gameType") String gameType) {
		return diceGameService.deleteAllGames(idPlayer, gameType);
	}
	
	//get Players Ranking
	@GetMapping("/ranking/{gameType}")
	@PreAuthorize("hasAuthority('get:player')")
	@ResponseStatus(HttpStatus.OK)  // 200
	public double getRankingList(@PathVariable("gameType") String gameType){
		return diceGameService.getPlayersWinPercentage(gameType);
	}
	
	//get player with worse win percentage
	@GetMapping("/ranking/loser/{gameType}")
	@PreAuthorize("hasAuthority('get:player')")
	@ResponseStatus(HttpStatus.OK)  // 200
	public Player getLoser(@PathVariable("gameType") String gameType){
		return diceGameService.getLoser(gameType);
	}
	
	//get Players Ranking
	@GetMapping("/ranking/winner/{gameType}")
	@ResponseStatus(HttpStatus.OK)  // 200
	@PreAuthorize("hasAuthority('get:player')")
	public Player getWinner(@PathVariable("gameType") String gameType){
		return diceGameService.getWinner(gameType);
	}
}
