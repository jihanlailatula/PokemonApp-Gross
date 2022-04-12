package jihanla.pokemon;

import java.util.*;

import jihanla.pokemon.model.pokemon;
import jihanla.pokemon.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
public class PokemonResource {
    private final PokemonService pokemonService;

    public PokemonResource(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<pokemon>> getAllPokemon(){
        List<pokemon> pokemons = pokemonService.findAllPokemon();
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<pokemon> getPokemonbyId(@PathVariable("id") Long id){
        pokemon Pokemon = pokemonService.findPokemonbyId(id);
        return new ResponseEntity<>(Pokemon,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<pokemon> addPokemon(@RequestBody pokemon Pokemon){
        List<Integer> probability = Arrays.asList(1,0);
        Random rand = new Random();
        Integer result = probability.get(rand.nextInt(probability.size()));
        System.out.println("ADD :"+result);
        Pokemon.setFibonacci(0L);
        Pokemon.setChanges(0L);
        String nick = Pokemon.getNickname();
        nick = nick + "-" + Long.toString(0);
        Pokemon.setNickname(nick);
        if (result == 1){
            pokemon newPokemon = pokemonService.addPokemon(Pokemon);
            return new ResponseEntity<>(newPokemon,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<pokemon> updatePokemon(@RequestBody pokemon Pokemon){
        long previous = Pokemon.getChanges();
        long current = Pokemon.getFibonacci();
        Pokemon.setChanges(current);
        if (previous == 0){
            Pokemon.setFibonacci(1L);}
        else{
            Pokemon.setFibonacci(previous+current);}
        current = Pokemon.getFibonacci();
        String nick = Pokemon.getNickname();
        nick = nick + "-" + Long.toString(current);
        Pokemon.setNickname(nick);
        pokemon updatePokemon = pokemonService.updatePokemon(Pokemon);
        return new ResponseEntity<>(updatePokemon,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") Long id){
        Random rand = new Random();
        int number = rand.nextInt(11);
        int count = 0;
        for (int i= 1; i<=number; i++){
            int res = number%i;
            if (res==0){
                count+= 1;
            }
            count+=0;
        }
        String temp = "";
        System.out.println("DELETE : "+temp);
        if (count ==2){
            temp = "DELETE SUCCESS -> "+Integer.toString(number);
            pokemonService.deletePokemon(id);
            return new ResponseEntity<>(temp,HttpStatus.OK);
        }
        temp = "DELETE FAILED -> "+Integer.toString(number);
        return new ResponseEntity<>(temp,HttpStatus.OK);


    }

}
