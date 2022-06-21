package com.cognixia.jump.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Player;
import com.cognixia.jump.repository.PlayerRepository;

@RestController
@RequestMapping("/api")
public class PlayerController {
	
	
	@Autowired
	PlayerRepository repo;
	
	
	@GetMapping("/player")
	public List<Player> getPlayers(){
		
		return repo.findAll();
	}
	
	@GetMapping("/player/{id}")
	public ResponseEntity<?> getPlayer(@PathVariable int id){
		
		Optional<Player> found = repo.findById(id);
		
		if(found.isEmpty()) {
			return ResponseEntity.status(404).body("Player with id = "+ id + " not found");
		}
		
		else {
			return ResponseEntity.status(200).body( found.get() );
		}
		
	}
	
	@PostMapping("/player")
	public ResponseEntity<?> createPlayer(@RequestBody Player player){
		
		player.setId(null);
		Player created = repo.save(player);
		
		return ResponseEntity.status(201).body(created);
	}
	
	@PutMapping("/player")
	public ResponseEntity<?> updatePlayer(@RequestBody Player player){
		
		boolean exists = repo.existsById(player.getId());
		
		// can do update if id exists
		if(exists) {
			
			Player updated = repo.save(player);
			return ResponseEntity.status(200).body(updated);
			
		}else { // id doesn't exist, can't do update
			return ResponseEntity.status(404).body("Can't perform update player doesn't exist");
		}
		
		
	}
	
	
	@DeleteMapping("/player/{id}")
	public ResponseEntity<?> deletePlayer(@PathVariable int id) {
		
		boolean exists = repo.existsById(id);
		
		if(exists) {
			repo.deleteById(id);
			return ResponseEntity.status(200).body("player with id = " + id + " was deleted");
			
		}else { 
			
			return ResponseEntity.status(404).body("Can't delete student doesn't exist");
		}
	}
	
	@PatchMapping("/player/team")
	public ResponseEntity<?> updateMajor(@PathParam(value= "id") int id, @PathParam(value= "team") String team){
		
		Optional<Player> found =  repo.findById(id);
		
		if(found.isEmpty()) {
			
			return ResponseEntity.status(404).body("player with id = " + id + " not found");
			
		}
		else {
			Player toUpdate = found.get();
			
			toUpdate.setTeam(team);
			
			repo.save(toUpdate);
			
			return ResponseEntity.status(200).body("The team that the player plays for was changed");
		}
		
	}
	
	@GetMapping("/player/team")
	public ResponseEntity<?> getPlayersByTeam(@PathParam(value = "team") String team){
		
		List<Player> allpPlayers = repo.findAll();
		List<Player> players = new ArrayList<Player>();
		System.out.println(team);
		
		for(Player p : allpPlayers) {
			System.out.println(p);
			System.out.println("\nInside for loop\nTeam is: " + p.getTeam() +"   team  =  " + team);
			if("Lakers"=="Lakers") {                     // having trouble with this part I do p.getTeam()==team
				players.add(p);							 // for some reason this never turns out true. Not sure what else to try.
			}
		}
		
		if(players.isEmpty()) {
			return ResponseEntity.status(404).body("No players with team: "+ team + " found");
		}
		else {
			return ResponseEntity.status(200).body(players);
		}
		
		
	}
	
	
	
}
	

