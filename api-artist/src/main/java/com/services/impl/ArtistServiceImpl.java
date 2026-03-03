package com.services; 

import com.dtos.ArtistDto;
import com.entities.Artist;
import com.mappers.ArtistMapper;
import com.repositories.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implémentation du service ArtistService.
 */
@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

	private final ArtistRepository repository;

	@Override
	public ArtistDto create(ArtistDto dto) {
		// Utilisation du Mapper pour transformer le DTO reçu en Entité pour la DB
		Artist artist = ArtistMapper.toEntity(dto);
		artist.setId(null);
		return ArtistMapper.toDTO(repository.save(artist));
	}

	@Override
	public ArtistDto getById(Long id) {
		Artist artist = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Artist not found"));
		return ArtistMapper.toDTO(artist);
	}

	@Override
	public List<ArtistDto> getAll() {
		return repository.findAll()
				.stream()
				.map(ArtistMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ArtistDto update(Long id, ArtistDto dto) {
		Artist existing = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Artist not found"));

		existing.setName(dto.getName());
		existing.setGenre(dto.getGenre());
		existing.setCountry(dto.getCountry());
		existing.setBirthDate(dto.getBirthDate());
		existing.setBiography(dto.getBiography());

		return ArtistMapper.toDTO(repository.save(existing));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}
}