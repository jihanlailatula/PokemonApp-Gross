package jihanla.pokemon.model;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class pokemon implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String name;
    private String nickname;
    private Long changes;
    private Long fibonacci;
    private String imgurl;
//    @Column(nullable = false,updatable = false)
    private Integer pokemonid;

    public Long getId() {
        return id;
    }

    public Integer getPokemonid() {
        return pokemonid;
    }

    public void setPokemonid(Integer pokemonid) {
        this.pokemonid = pokemonid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getChanges() {
        return changes;
    }

    public void setChanges(Long changes) {
        this.changes = changes;
    }

    public Long getFibonacci() {
        return fibonacci;
    }

    public void setFibonacci(Long fibonacci) {
        this.fibonacci = fibonacci;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
