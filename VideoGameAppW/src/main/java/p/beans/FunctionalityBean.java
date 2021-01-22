package p.beans;
import javax.ejb.*;
import javax.persistence.*;
import java.util.*;

import p.entities.DeveloperCompany;
import p.interfaces.*;
import p.entities.*;
import p.dtos.*;
@Stateless
@Local(Functionality.class)
public class FunctionalityBean implements Functionality{
    @PersistenceContext(unitName = "de")
    private EntityManager manager;
    public p.dtos.DeveloperCompanyDTO findCompany(Long id) { return company2DTO(manager.find(DeveloperCompany.class, id)); }
    public VideoGameDTO findGame(Long id) { return game2DTO(manager.find(VideoGame.class, id)); }
    public p.dtos.DeveloperCompanyDTO createCompany(String name) {
        DeveloperCompany companyD = new DeveloperCompany();
        companyD.setName(name);
        manager.persist(companyD);
        return company2DTO(companyD);
    }
    public VideoGameDTO createGame(String title, int year, String type, Long companyId) {
        DeveloperCompany companyD = manager.find(DeveloperCompany.class, companyId);
        if (companyD == null) return null;
        VideoGame gameV = new VideoGame();
        gameV.setTitle(title);
        gameV.setYear(year);
        gameV.setType(type);
        gameV.setCompany(companyD);
        manager.persist(gameV);
        companyD.getGames().add(gameV);
        manager.merge(companyD);
        return game2DTO(gameV);
    }
    public p.dtos.DeveloperCompanyDTO deleteCompany(Long id) {
        DeveloperCompany companyD = manager.find(DeveloperCompany.class, id);
        p.dtos.DeveloperCompanyDTO company = company2DTO(companyD);
        if (companyD == null) return null;
        companyD.getGames().clear();
        manager.remove(companyD);
        return company;
    }
    public VideoGameDTO deleteGame(Long id) {
        VideoGame gameV = manager.find(VideoGame.class, id);
        VideoGameDTO game = game2DTO(gameV);
        if (gameV == null) return null;
        DeveloperCompany deptE = gameV.getCompany();
        deptE.getGames().remove(gameV);
        return game;
    }
    public p.dtos.DeveloperCompanyDTO updateCompany(p.dtos.DeveloperCompanyDTO company) {
        if (company == null) return null;
        DeveloperCompany companyD = manager.find(DeveloperCompany.class, company.id);
        if (companyD == null) return null;
        p.dtos.DeveloperCompanyDTO old = company2DTO(companyD);
        companyD.setName(company.name);
        manager.merge(companyD);
        return old;
    }
    public VideoGameDTO updateGame(VideoGameDTO game) {
        if (game == null) return null;
        VideoGame gameV = manager.find(VideoGame.class, game.id);
        if (gameV == null) return null;
        VideoGameDTO old = game2DTO(gameV);
        gameV.setTitle(game.title);
        gameV.setYear(game.year);
        gameV.setType(game.type);
        manager.merge(gameV);
        return old;
    }
    public List<p.dtos.DeveloperCompanyDTO> findAllCompanies() {
        TypedQuery<DeveloperCompany> query = manager.createQuery(
            "select d from DeveloperCompany d ", DeveloperCompany.class);
        List<p.dtos.DeveloperCompanyDTO> companies = (List<p.dtos.DeveloperCompanyDTO>)(new ArrayList<p.dtos.DeveloperCompanyDTO>());
        for (DeveloperCompany dept : query.getResultList()) companies.add(company2DTO(dept));
        manager.flush();
        return companies;
    }
    public List<VideoGameDTO> findAllGames() {
        TypedQuery<VideoGame> query = manager.createQuery(
            "select e from VideoGame e ", VideoGame.class);
        List<VideoGameDTO> games = (List<VideoGameDTO>)(new ArrayList<VideoGameDTO>());
        for (VideoGame game : query.getResultList()) games.add(game2DTO(game));
        manager.flush();
        return games;
    }
    private p.dtos.DeveloperCompanyDTO company2DTO(DeveloperCompany company) {
        if (company == null) return null;
        p.dtos.DeveloperCompanyDTO companyDTO = new p.dtos.DeveloperCompanyDTO();
        companyDTO.id  = company.getId();
        companyDTO.name = company.getName();
        if(company != null && company.getGames() != null)
            for (VideoGame game : company.getGames()) companyDTO.games.add(game2DTO(game));
                return companyDTO;
    }
    private VideoGameDTO game2DTO(VideoGame game) {
        if (game == null) return null;
        VideoGameDTO gameDTO = new VideoGameDTO();
        gameDTO.id = game.getId();
        gameDTO.title = game.getTitle();
        gameDTO.year = game.getYear();
        gameDTO.type = game.getType();
        gameDTO.companyId = game.getCompany().getId();
        return gameDTO;
    }
}
