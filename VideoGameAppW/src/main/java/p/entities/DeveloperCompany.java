package p.entities;
import java.io.*;
import java.util.*;
import javax.persistence.*;
@Entity
@Table(name="company")
public class DeveloperCompany implements Serializable {
    private String name = "";
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY, mappedBy = "company")
    private List<VideoGame> games;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Collection<VideoGame> getGames() { return games; }
    public void setGames(List<VideoGame> game) { this.games = game; }

}
