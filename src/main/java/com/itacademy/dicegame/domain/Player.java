package com.itacademy.dicegame.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.decimal4j.util.DoubleRounder;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Players")
public class Player {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="player_id")
	private int id;
	
	@NotBlank
	@Column(name="name")
	private String name;
	
	@CreationTimestamp
	@Column(name="picture_registrationdate",  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	private Date creationDate;
	
	//% Success Games
	@Column(name="winPercentage")
	private double winPercentage;
	
	//List of the DiceGames played List<DiceGame>
	@OneToMany(mappedBy = "player")
	private List<DiceGame> diceGameList;
	
	public Player() {
		this.name="ANÒNIM";
	}
	
	public Player(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@JsonIgnore
	public List<DiceGame> getDiceGameList() {
		return diceGameList;
	}

	public void setWinPercentage() {
		double winPercentage;
		if(diceGameList != null) {
			double wins = diceGameList.stream().filter(dg -> dg.getResult()==true).count();
			double total = diceGameList.size();
			winPercentage = (wins/total)*100;
		}else {
			winPercentage=0;
		}
		this.winPercentage =  DoubleRounder.round(winPercentage, 2);
	}
	
	public double getWinPercentage() {
		return this.winPercentage;
	}
	
}
