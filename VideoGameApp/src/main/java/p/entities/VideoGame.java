package p.entities;
import java.io.*;
import javax.persistence.*;
@Entity
@Table(name="game")
public class VideoGame implements Serializable {
    private String title = "";
    private int year = -1;
    private String type = "";
    @ManyToOne
    @JoinColumn(name="idcompany")
    private DeveloperCompany company;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    public VideoGame() { }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String name) { this.title = name; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public DeveloperCompany getCompany() { return company; }
    public void setCompany(DeveloperCompany company) { this.company = company; }

}
