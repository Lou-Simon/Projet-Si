package com.controllers; 
import com.dtos.ArtistDto;       
import com.services.ArtistService; 
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST exposant les endpoints de gestion des artistes.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/artists")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ArtistController {

	private final ArtistService service;

	@PostMapping
	public ArtistDto create(@RequestBody ArtistDto dto) {
		return service.create(dto);
	}

	@GetMapping("/{id}")
	public ArtistDto getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@GetMapping
	public List<ArtistDto> getAll() {
		return service.getAll();
	}

	@PutMapping("/{id}")
	public ArtistDto update(@PathVariable Long id, @RequestBody ArtistDto dto) {
		return service.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}