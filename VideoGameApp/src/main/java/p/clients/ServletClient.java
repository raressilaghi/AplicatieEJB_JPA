package p.clients;
import java.io.*;
import java.util.*;
import javax.ejb.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import p.interfaces.*;
import p.beans.*;
@WebServlet("/controller")
public class ServletClient extends HttpServlet {
    @EJB
    private Functionality functionality;
    private BeanJSP bean;
    RequestDispatcher rdIndex, rdView, rdAddCompany, rdUpdateCompany, rdAddGame, rdUpdateGame;
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
                                             throws ServletException, IOException {
        bean = (BeanJSP) req.getSession().getAttribute("bean");
        if (bean == null) return;
        List<String> pars = Collections.list(req.getParameterNames());
        for (int i = 0; i < pars.size(); i++) {
            String p = pars.get(i);
            int k = p.indexOf("_");
            if (k > 0) {
                bean.id = Long.parseLong(p.substring(k + 1));
                pars.set(i, p.substring(0, k));
            }
        }
        String companyName = req.getParameter("companyName"); if (companyName == null) companyName = "";
        String gameTitle = req.getParameter("gameTitle"); if (gameTitle == null) gameTitle = "";
        String gameType = req.getParameter("gameType"); if (gameType == null) gameType = "";
        int gameYear = -1;
        try { gameYear = Integer.parseInt(req.getParameter("gameYear")); } catch (Exception e) {};
        //System.out.println(bean+" | pars:"+pars+"| deptName:"+deptName+"| emplName:"+emplName+"| emplAge:"+emplAge+"| emplSex:"+emplSex+"|");
        rdIndex = req.getRequestDispatcher("/Index.jsp");
        rdView = req.getRequestDispatcher("/View.jsp");
        rdAddCompany = req.getRequestDispatcher("/AddCompany.jsp");
        rdAddGame = req.getRequestDispatcher("/AddGame.jsp");
        rdUpdateCompany = req.getRequestDispatcher("/UpdateCompany.jsp");
        rdUpdateGame = req.getRequestDispatcher("/UpdateGame.jsp");
        if (pars.contains("start")) {
            bean.companies = functionality.findAllCompanies();
            rdView.forward(req, res);
        } else if (pars.contains("cancel")) {
            bean.companies = functionality.findAllCompanies();
            rdView.forward(req, res);
        } else if (pars.contains("viewCompanyAdd")) {
            rdAddCompany.forward(req, res);
        } else if (pars.contains("companyAdd")) {
            bean.company = functionality.createCompany(companyName);
            bean.companies = functionality.findAllCompanies();
            rdView.forward(req, res);
        } else if (pars.contains("viewGameAdd")) {
            bean.company = functionality.findCompany(bean.id);
            rdAddGame.forward(req, res);
        } else if (pars.contains("gameAdd")) {
            bean.game = functionality.createGame(gameTitle, gameYear, gameType, bean.id);
            bean.companies = functionality.findAllCompanies();
            rdView.forward(req, res);
        } else if (pars.contains("viewCompanyDelete")) {
            bean.company = functionality.deleteCompany(bean.id);
            bean.companies = functionality.findAllCompanies();
            rdView.forward(req, res);
        } else if (pars.contains("viewGameDelete")) {
            bean.game = functionality.deleteGame(bean.id);
            bean.companies = functionality.findAllCompanies();
            rdView.forward(req, res);
        } else if (pars.contains("viewCompanyUpdate")) {
            bean.company = functionality.findCompany(bean.id);
            rdUpdateCompany.forward(req, res);
        } else if (pars.contains("companyUpdate")) {
            bean.company.name = companyName;
            bean.company = functionality.updateCompany(bean.company);
            bean.companies = functionality.findAllCompanies();
            rdView.forward(req, res);
        } else if (pars.contains("viewGameUpdate")) {
            bean.game = functionality.findGame(bean.id);
            bean.company = functionality.findCompany(bean.game.companyId);
            rdUpdateGame.forward(req, res);
        } else if (pars.contains("gameUpdate")) {
            bean.game.title = gameTitle;
            bean.game.year = gameYear;
            bean.game.type = gameType;
            bean.game = functionality.updateGame(bean.game);
            bean.companies = functionality.findAllCompanies();
            rdView.forward(req, res);
        } else {
            rdView.forward(req, res);
        }
    }
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
                doPost(request, response);
    }
}
