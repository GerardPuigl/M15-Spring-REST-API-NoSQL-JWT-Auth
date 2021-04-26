package com.itacademy.dicegame.service;

import java.util.List;



import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.dicegame.domain.DiceGame;
import com.itacademy.dicegame.domain.Player;
import com.itacademy.dicegame.persistence.DiceGameRepository;
import com.itacademy.dicegame.persistence.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private DiceGameRepository diceGameRepository;

	// create Player
	public Player createPlayer(Player player) {
		playerRepository.save(player);
		return getPlayerById(player.getId());
	}

	// modify Player
	public Player modifyPlayer(Player player) {
		Player playerDb = playerRepository.findById(player.getId()).get();
		playerDb.setName(player.getName());
		playerRepository.save(playerDb);
		return getPlayerById(player.getId());
	}

	// get One Player by id
	public Player getPlayerById(String id_Player) {
		return playerRepository.findById(id_Player).get();
	}

	// get all players
	public List<Player> getAll() {
		return playerRepository.findAll();
	}

	// delete player
	public String deleteById(String idPlayer) {
		playerRepository.delete(getPlayerById(idPlayer));
		return "Usuari eliminat correctament";
	}
	
	// return total Win Average
	public double getPlayersWinPercentage() {		
		double winPercentage = 0;
		List<DiceGame> diceGameList = diceGameRepository.findAll();
		if(diceGameList != null) {
			double wins = diceGameList.stream().filter(dg -> dg.getResult()==true).count();
			double total = diceGameList.size();
			winPercentage = (wins/total)*100;
		}
		return DoubleRounder.round(winPercentage, 2);
	}

	// get player with worse win percentage
	public Player getLoser() {
		return playerRepository.findTopByOrderByWinPercentage();
	}
	
	// get player the best win percentage
	public Player getWinner() {
		return playerRepository.findTopByOrderByWinPercentageDesc();
	}
}
