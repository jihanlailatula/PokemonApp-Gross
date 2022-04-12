package jihanla.pokemon.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jihanla.pokemon.model.pokemonList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PokemonAPIservice {

    private static URL url;
//    private pokemonList pokemonlist;

    static {
        try {
            url = new URL("https://pokeapi.co/api/v2/pokemon");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public pokemonList getAllPokemon(int id) throws IOException {
//        System.out.println(" In method");
        pokemonList pokemonlist = new pokemonList();
        ObjectMapper mapper = new ObjectMapper();
        String strid = Integer.toString(id);
        pokemonlist.setId(id);
        URL newurl = new URL(url + "/" + strid);
        Map<String, Object> map = mapper.readValue(newurl, Map.class);
        System.out.println(" Onto name");
        //===================!! GET POKEMON NAME !!=============
        System.out.println(" Get name "+id);
        Object Nameresult = map.get("forms");

        String resName = Nameresult.toString()
                    .replace("[{", "");
        resName = resName.substring(0, resName.indexOf(","));

        String name = resName.replaceAll("name=", "");
        pokemonlist.setPokename(name.substring(0, 1).toUpperCase() + name.substring(1));

        //==============!! GET POKEMON IMAGE !!=================
        System.out.println(" Get image "+id);
        Object Imageresult = map.get("sprites");

        String resImg = Imageresult.toString()
                        .replace("},", "}@");
        String subImg = resImg.substring(0, resImg.indexOf("@"))
                        .replace("=", ":")
                        .replaceAll(" ", "")
                        .replaceAll("<.*?>|\\u00a0", "")
                        .replaceAll(":h", ":\"h")
                        .replaceAll("g,", "g\",")
                        .replaceAll(",other", "}@");

        String[] temp = subImg.split("@",2);
        subImg = temp[0];

        JSONObject jsonObjectImg = new JSONObject(subImg);
//        System.out.println("JSON OBJECT CREATED");
        String img = jsonObjectImg.getString("front_default");

        pokemonlist.setImgurl(img);
        //===================!! GET POKEMON MOVES !!=============
        System.out.println(" Get moves "+id);
        Object resultMoves = (Object) map.get("moves");

        String resMoves = resultMoves.toString()
                    .replaceAll("\\[\\{", "{")
                    .replaceAll("}]", "}")
                    .replaceAll("=", ":")
                    .replaceAll("<.*?>|\\u00a0", "")
                    .replaceAll("}}}, \\{", "}}}}, {")
                    .replaceAll("/}", "/\"}");

        int limit = 4;
        String[] Moves = resMoves.split("}}}, \\{", limit);
        List<String> moves = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            Moves[i] = Moves[i]
                    .replaceAll("\\{move:| move:", "")
                    .replaceAll("move:", "")
                    .substring(0, 25)
                    .replaceAll(", ", "}@");
            Moves[i] = Moves[i].substring(0, Moves[i].indexOf("@"))
                    .replaceAll("\\{name:", "")
                    .replaceAll("}", "");
            moves.add(Moves[i]);

        }
        pokemonlist.setMoves(moves);

        //===================!! GET POKEMON TYPES !!=============
        System.out.println(" Get types "+id);
        Object resultTypes = (Object) map.get("types");
        String resTypes = resultTypes.toString()
                        .replaceAll("}, ", "},[")
                        .replaceAll("},", "@");
        String[] Types = resTypes.split("@", 2);
        List<String> types = new ArrayList<>();;

        Types[0] = Types[0].replaceAll("\\[\\{", "")
                    .replaceAll("}", "");
        String[] tempTypes = Types[0].split("\\{", 2);
        tempTypes = tempTypes[1].split(",", 2);
        tempTypes = tempTypes[0].split("=", 2);
        types.add(tempTypes[1]);


        if (Types.length > 1) {
            Types[1] = Types[1].replaceAll("\\[\\{", "")
                        .replaceAll("}", "");
            tempTypes = Types[1].split("\\{", 2);
            tempTypes = tempTypes[1].split(",", 2);
            tempTypes = tempTypes[0].split("=", 2);
            types.add(tempTypes[1]);
        }
        pokemonlist.setTypes(types);
        return pokemonlist;
    }



}
