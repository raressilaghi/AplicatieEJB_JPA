package p.dtos;
import java.io.*;
import java.util.*;
public class DeveloperCompanyDTO implements Serializable {
    public String name = "";
    public Collection<VideoGameDTO> games = (Collection<VideoGameDTO>)(new ArrayList<VideoGameDTO>());
    public Long id;

}
