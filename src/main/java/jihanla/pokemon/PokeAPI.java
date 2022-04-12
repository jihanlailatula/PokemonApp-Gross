package jihanla.pokemon;



import jihanla.pokemon.model.pokemonList;
import jihanla.pokemon.service.PokemonAPIservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.*;


@RestController
@RequestMapping("/pokeAPI")
public class PokeAPI {
    private  final PokemonAPIservice pokemonAPIservice;

    public PokeAPI(PokemonAPIservice pokemonAPIservice) {
        this.pokemonAPIservice = pokemonAPIservice;
    }


//    private static URL url;
//
//    static {
//        try {
//            url = new URL("https://pokeapi.co/api/v2/pokemon");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }

//    @GetMapping("/test")
//    public ResponseEntity<ArrayList<String>> getAllPokemon() throws IOException {
//
//        //===================!! GET POKEMON NAME !!=============
//        ObjectMapper mapper = new ObjectMapper();
//
//        Map<String,Object> map = mapper.readValue(url, Map.class);
//        Object result = (Object) map.get("results");
//        String res = result.toString();
//        res = res.replace("[", "");
//        res = res.replace("]", "");
//        res = res.replace("=", ":");
//        res= res.replaceAll(" ", "");
//        res= res.replaceAll("<.*?>|\\u00a0", "");
//        res= res.replaceAll("https://pokeapi.co/api/v2/pokemon/", "");
//        res= res.replace("/", "");
//        res = res.replace("},","}@");
//        String[] jsons = res.split("@", 20);
//
//        ArrayList<JSONObject> json = new ArrayList<JSONObject>();
//        for (int i = 0; i< jsons.length;i++){
//            JSONObject jsonObject = new JSONObject(jsons[i]);
//            json.add(jsonObject);
//        }
//        ArrayList<String> names = new ArrayList<String>();
//        for (int i = 0; i< jsons.length;i++){
//            names.add(json.get(i).getString("name"));
//        }
//        //==============!! GET POKEMON IMAGE !!=================
//
//
//
//        return new ResponseEntity<>(names,HttpStatus.OK);
//
//    }
    @GetMapping("/all")
    public ResponseEntity<List<pokemonList>> getPokemon() throws IOException  {
//        ObjectMapper mapper = new ObjectMapper();
//        String strid = Integer.toString(id);
//        pokemonlist.setId(id);
//        url = new URL(url+"/"+strid);
//        Map<String,Object> map = mapper.readValue(url, Map.class);
//
//        //===================!! GET POKEMON NAME !!=============
//        Object Nameresult = map.get("forms");
//
//        String resName = Nameresult.toString();
//        resName = resName.replace("[{","");
//        resName = resName.substring(0,resName.indexOf(","));
//        String name = resName.replaceAll("name=","");
//        pokemonlist.setPokename(name);
//
//        //==============!! GET POKEMON IMAGE !!=================
//        Object Imageresult = map.get("sprites");
//
//        String resImg = Imageresult.toString();
//        resImg = resImg.replace("},", "}@");
//        String subImg = resImg.substring(0,resImg.indexOf("@"));
//
//        subImg = subImg.replace("=", ":");
//        subImg = subImg.replaceAll(" ", "");
//        subImg = subImg.replaceAll("<.*?>|\\u00a0", "");
//        subImg = subImg.replaceAll(":h", ":\"h" );
//        subImg = subImg.replaceAll("g,","g\",");
//        subImg = subImg.replaceAll(",other","}other");
//        subImg = subImg.substring(0,resImg.indexOf("other"));
//        JSONObject jsonObjectImg = new JSONObject(subImg);
//
//        String img = jsonObjectImg.getString("front_default");
//
//        pokemonlist.setImgurl(img);
//        //===================!! GET POKEMON MOVES !!=============
//        Object resultMoves = (Object) map.get("moves");
//
//        String resMoves = resultMoves.toString();
//
//        resMoves = resMoves.replaceAll("\\[\\{","{");
//        resMoves = resMoves.replaceAll("}]","}") ;
//        resMoves = resMoves.replaceAll("=", ":");
//
//        resMoves = resMoves.replaceAll("<.*?>|\\u00a0", "");
//        resMoves = resMoves.replaceAll("}}}, \\{", "}}}}, {");
//        resMoves = resMoves.replaceAll("/}", "/\"}");
//
//        int limit = 4;
//        String[] Moves = resMoves.split("}}}, \\{", limit);
//        ArrayList<String> moves = new ArrayList<String>();
//        for (int i = 0; i <limit;i++){
//            Moves[i] = Moves[i].replaceAll("\\{move:", "");
//            Moves[i] = Moves[i].replaceAll("move:", "");
//            Moves[i] = Moves[i].substring(0, 25);
//            Moves[i] = Moves[i].replaceAll(", ", "}@");
//            Moves[i] = Moves[i].substring(0, Moves[i].indexOf("@"));
//            Moves[i] = Moves[i].replaceAll("\\{name:", "");
//            Moves[i] = Moves[i].replaceAll("}", "");
//            moves.add(Moves[i]);
//        }
//        pokemonlist.setMoves(moves);
//        //===================!! GET POKEMON TYPES !!=============
//        Object resultTypes = (Object) map.get("types");
//        limit = 2;
//        String resTypes = resultTypes.toString();
//        resTypes =  resTypes.replaceAll("}, ","},[");
//        resTypes =  resTypes.replaceAll("},","@");
//        String[] Types =  resTypes.split("@",2);
//        ArrayList<String> types = new ArrayList<>();
//        for (int i = 0; i<limit;i++){
//            Types[i] = Types[i].replaceAll("\\[\\{", "");
//            Types[i] = Types[i].replaceAll("}", "");
//            String[] tempTypes = Types[i].split("\\{",2);
//            String[] tempTypes2 = tempTypes[1].split(",",2);
//            String[] tempTypes3  = tempTypes2[0].split("=",2);
//            types.add(tempTypes3[1]);
//        }
//        pokemonlist.setTypes(types);
//        //        ============ RETURN ===========================
//        return new ResponseEntity<>(pokemonlist,HttpStatus.OK);
        List<pokemonList> poke = new ArrayList<>();
        System.out.println("In API" );
        for (int i = 13; i<36;i=i+2){
            poke.add(pokemonAPIservice.getAllPokemon(i));
            System.out.println("in For");
        }
        System.out.println("to return");
        return new ResponseEntity<>(poke,HttpStatus.OK);
    }

//    @GetMapping("/test2/{id}")
//    public Object tester2(@PathVariable("id") int id) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        String strid = Integer.toString(id);
//        url = new URL(url+"/"+strid);
//        Map<String,Object> map = mapper.readValue(url, Map.class);
//        Object result = (Object) map.get("moves");
//
//        String res = result.toString();
//
//        res = res.replaceAll("\\[\\{","{");
//        res = res.replaceAll("}]","}") ;
//        res = res.replaceAll("=", ":");
//
//        res= res.replaceAll("<.*?>|\\u00a0", "");
//        res= res.replaceAll("}}}, \\{", "}}}}, {");
//        res= res.replaceAll("/}", "/\"}");
//
//        int limit = 4;
//        String[] jsons = res.split("}}}, \\{", limit);
//        ArrayList<String> moves = new ArrayList<String>();
//        for (int i = 0; i <limit;i++){
//            jsons[i] = jsons[i].replaceAll("\\{move:", "");
//            jsons[i] = jsons[i].replaceAll("move:", "");
//            jsons[i] = jsons[i].substring(0, 25);
//            jsons[i] = jsons[i].replaceAll(", ", "}@");
//            jsons[i] = jsons[i].substring(0, jsons[i].indexOf("@"));
//            jsons[i] = jsons[i].replaceAll("\\{name:", "");
//            jsons[i] = jsons[i].replaceAll("}", "");
//            moves.add(jsons[i]);
//        }
//        return moves;
//    }



}
