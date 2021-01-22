package p.interfaces;

import p.dtos.DeveloperCompanyDTO;
import p.dtos.VideoGameDTO;

import java.util.List;

public interface FunctionalityR {
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
