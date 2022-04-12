package jihanla.pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class pokemonList implements Serializable {

    private Integer id;
    private String name;
    private String imgurl;
    private List<String> moves;
    private List<String> types;


    public String getname() {
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPokename(String name) {
        this.name = name;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }



    public int getId() {
        return id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public List<String> getMoves() {
        return moves;
    }

    public void setMoves(List<String> moves) {
        this.moves = moves;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
