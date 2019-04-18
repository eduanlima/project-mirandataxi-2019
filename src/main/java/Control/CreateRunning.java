package Control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CreateRunning", urlPatterns = {"/CreateRunning"})
public class CreateRunning extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        
        int idClient = Integer.parseInt(session.getAttribute("userId").toString());
        
        String passenger = request.getParameter("passenger");
        String data = request.getParameter("dataCor");
        String hour = request.getParameter("hourCor");
        String numFlight = request.getParameter("numFlight");
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        String note = request.getParameter("note");
        String babyChair = request.getParameter("babyChair");
        String wedding = request.getParameter("wedding");
        String nameWedding = request.getParameter("nameWedding");
        String reception = request.getParameter("reception");
        String nameReception = request.getParameter("nameReception");
        
       //{"data":"03/05/2018","hour":"12:50","numFlight":"","origin":"Rua Rocha, 23, Bela Vista, São Paulo","destination":"Rua Rocha, 23, Bela Vista, São Paulo","note":"OBS","babyChair":1,"wedding":2,"nameWedding":"Eduan & Maria","reception":3,"":"Maria"} 
        String valuesRunnig = "{\"idClient\":"+idClient+",\"passenger\":\""+passenger+"\",\"data\":\""+data+"\",\"hour\":\""+hour+"\",\"numFlight\":\""+numFlight+"\","+
                               "\"origin\":\""+origin+"\",\"destination\":\""+destination+"\",\"note\":\""+note+"\","+
                               "\"babyChair\":"+babyChair+",\"wedding\":"+wedding+",\"nameWedding\":\""+nameWedding+"\","+
                               "\"reception\":"+reception+",\"nameReception\":\""+nameReception+"\"}";
        
        session.setAttribute("valuesRunning", valuesRunnig);
        
        System.out.println(""+session.getAttribute("valuesRunning"));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
