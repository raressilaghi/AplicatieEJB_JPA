package p.interfaces;
import java.util.*;
import p.dtos.*;
public interface Functionality {
    public DeveloperCompanyDTO createCompany(String name);
    public VideoGameDTO createGame(String title, int year, String type, Long companyId);
    public DeveloperCompanyDTO findCompany(Long id);
    public VideoGameDTO findGame(Long id);
    public DeveloperCompanyDTO deleteCompany(Long id);
    public VideoGameDTO deleteGame(Long id);
    public DeveloperCompanyDTO updateCompany(DeveloperCompanyDTO company);
    public VideoGameDTO updateGame(VideoGameDTO game);
    public List<DeveloperCompanyDTO> findAllCompanies();
    public List<VideoGameDTO> findAllGames();
}
