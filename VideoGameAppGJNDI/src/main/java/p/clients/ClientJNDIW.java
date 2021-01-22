package p.clients;

import p.beans.BeanJSP;
import p.interfaces.FunctionalityR;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@WebServlet("/clientJNDI")
public class ClientJNDIW extends HttpServlet {
    private Properties JNDIProps;
    private Context context;
    private FunctionalityR functionalityR;
    private BeanJSP bean;
    RequestDispatcher rdIndex, rdView, rdAddCompany, rdUpdateCompany, rdAddGame, rdUpdateGame;

    public ClientJNDIW() throws NamingException {
        JNDIProps = new Properties();
        JNDIProps.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        JNDIProps.put("org.omg.CORBA.ORBInitialHost", "localhost");
        JNDIProps.put("org.omg.CORBA.ORBInitialPort", "3700");
        context = new InitialContext(JNDIProps);
        functionalityR = (FunctionalityR) context.lookup("java:global/VideoGameAppGJNDI-1.0-SNAPSHOT/FunctionalityBean!p.interfaces.FunctionalityR");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        bean = (BeanJSP) request.getSession().getAttribute("bean");
        if (bean == null) return;
        List<String> pars = Collections.list(request.getParameterNames());
        for (int i = 0; i < pars.size(); i++) {
            String p = pars.get(i);
            int k = p.indexOf("_");
            if (k > 0) {
                bean.id = Long.parseLong(p.substring(k + 1));
                pars.set(i, p.substring(0, k));
            }
        }
        String companyName = request.getParameter("companyName"); if (companyName == null) companyName = "";
        String gameTitle = request.getParameter("gameTitle"); if (gameTitle == null) gameTitle = "";
        String gameType = request.getParameter("gameType"); if (gameType == null) gameType = "";
        int gameYear = -1;
        try { gameYear = Integer.parseInt(request.getParameter("gameYear")); } catch (Exception e) {};
        rdIndex = request.getRequestDispatcher("/Index.jsp");
        rdView = request.getRequestDispatcher("/View.jsp");
        rdAddCompany = request.getRequestDispatcher("/AddCompany.jsp");
        rdAddGame = request.getRequestDispatcher("/AddGame.jsp");
        rdUpdateCompany = request.getRequestDispatcher("/UpdateCompany.jsp");
        rdUpdateGame = request.getRequestDispatcher("/UpdateGame.jsp");
        if (pars.contains("start")) {
            bean.companies = functionalityR.findAllCompanies();
            rdView.forward(request, response);
        } else if (pars.contains("cancel")) {
            bean.companies = functionalityR.findAllCompanies();
            rdView.forward(request, response);
        } else if (pars.contains("viewCompanyAdd")) {
            rdAddCompany.forward(request, response);
        } else if (pars.contains("companyAdd")) {
            bean.company = functionalityR.createCompany(companyName);
            bean.companies = functionalityR.findAllCompanies();
            rdView.forward(request, response);
        } else if (pars.contains("viewGameAdd")) {
            bean.company = functionalityR.findCompany(bean.id);
            rdAddGame.forward(request, response);
        } else if (pars.contains("gameAdd")) {
            bean.game = functionalityR.createGame(gameTitle, gameYear, gameType, bean.id);
            bean.companies = functionalityR.findAllCompanies();
            rdView.forward(request, response);
        } else if (pars.contains("viewCompanyDelete")) {
            bean.company = functionalityR.deleteCompany(bean.id);
            bean.companies = functionalityR.findAllCompanies();
            rdView.forward(request, response);
        } else if (pars.contains("viewGameDelete")) {
            bean.game = functionalityR.deleteGame(bean.id);
            bean.companies = functionalityR.findAllCompanies();
            rdView.forward(request, response);
        } else if (pars.contains("viewCompanyUpdate")) {
            bean.company = functionalityR.findCompany(bean.id);
            rdUpdateCompany.forward(request, response);
        } else if (pars.contains("companyUpdate")) {
            bean.company.name = companyName;
            bean.company = functionalityR.updateCompany(bean.company);
            bean.companies = functionalityR.findAllCompanies();
            rdView.forward(request, response);
        } else if (pars.contains("viewGameUpdate")) {
            bean.game = functionalityR.findGame(bean.id);
            bean.company = functionalityR.findCompany(bean.game.companyId);
            rdUpdateGame.forward(request, response);
        } else if (pars.contains("gameUpdate")) {
            bean.game.title = gameTitle;
            bean.game.year = gameYear;
            bean.game.type = gameType;
            bean.game = functionalityR.updateGame(bean.game);
            bean.companies = functionalityR.findAllCompanies();
            rdView.forward(request, response);
        } else {
            rdView.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}



