package jihanla.pokemon.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import  jihanla.pokemon.model.pokemon;

import java.util.Optional;

public interface PokemonRepo extends JpaRepository<pokemon,Long> {
    void deletePokemonById(Long id);

    Optional<pokemon> findPokemonById(Long id);
}
