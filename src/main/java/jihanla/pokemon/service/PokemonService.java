package jihanla.pokemon.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.*;

import jakarta.transaction.Transactional;
import jihanla.pokemon.exception.UserNotFoundException;
import jihanla.pokemon.model.pokemon;
import jihanla.pokemon.repo.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class PokemonService {
    private final PokemonRepo pokemonRepo;

    @Autowired
    public PokemonService(PokemonRepo pokemonRepo) {
       this.pokemonRepo= pokemonRepo;
    }

    public pokemon addPokemon(pokemon Pokemon){
        return pokemonRepo.save(Pokemon);
    }

    public List<pokemon> findAllPokemon(){
        return pokemonRepo.findAll();
    }

    public pokemon updatePokemon(pokemon Pokemon){
        return pokemonRepo.save(Pokemon);
    }

    public void deletePokemon(Long id){
        pokemonRepo.deletePokemonById(id);
    }

    public pokemon findPokemonbyId(Long id){
        return pokemonRepo.findPokemonById(id)
                .orElseThrow(()-> new UserNotFoundException("Pokemon not by id "+id+" was not found."));
    }


}
