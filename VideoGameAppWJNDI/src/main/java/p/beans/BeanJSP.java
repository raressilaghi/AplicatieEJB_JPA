package p.beans;
import java.io.*;
import java.util.*;
import p.dtos.*;
public class BeanJSP implements Serializable {
    public DeveloperCompanyDTO company;
    public VideoGameDTO game;
    public List<DeveloperCompanyDTO> companies;
    public long id;

}
