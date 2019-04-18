/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.ClassCar;
import Model.Client;
import Model.ItemService;
import Model.PaymentMain;
import Model.Running;
import ModelDAO.ClassCarDAO;
import ModelDAO.ItemServiceDAO;
import ModelDAO.PaymentMainDAO;
import ModelDAO.RunningDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author GRAFIX
 */
@WebServlet(name = "ReadRunning", urlPatterns = {"/ReadRunning"})
public class ReadRunning extends HttpServlet {

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
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        
        int idClient = Integer.parseInt(session.getAttribute("userId").toString());
        
        PrintWriter out = response.getWriter();
        Client client = new Client();
        client.setId(idClient);
        
        List<Running> listRunning;
        
        RunningDAO runningDAO = new RunningDAO();
        
        listRunning = runningDAO.readRunning(client);
        
        List<ItemService> listItemService;
        ItemServiceDAO itemServiceDAO = new ItemServiceDAO();
        
        ClassCar classCar = new ClassCar();
        ClassCarDAO classCarDAO = new ClassCarDAO();
        
        PaymentMain paymentMain;
        PaymentMainDAO paymentMainDAO = new PaymentMainDAO();
        String paymentAux;
        
        Gson gson = new Gson();
        StringBuilder gsonRunning;
        StringBuilder gsonListServ;
        StringBuilder gsonPayment;
        StringBuilder gsonResponse;
        
        JSONObject jsonResponse;
        JSONObject jsonPayment;
        JSONObject jsonListService;
        List<JSONObject> listResponse = new ArrayList<>();
        
        for(Running r: listRunning){
            classCar.setId(r.getClassCar().getId());
            classCar = classCarDAO.readClassCar(r);
            r.setClassCar(classCar);
            
            try{
                gsonRunning = new StringBuilder(gson.toJson(r));
                jsonResponse = new JSONObject(gsonRunning.toString());

                listItemService = itemServiceDAO.readItemService(r);
                gsonListServ = new StringBuilder(gson.toJson(listItemService));
                gsonListServ.insert(0,"{item:");
                gsonListServ.append("}");

                jsonListService = new JSONObject(gsonListServ.toString());
                gsonResponse = new StringBuilder(gsonRunning);
                gsonResponse.append(",");
                gsonResponse.append("gsonListServ");
                jsonResponse.put("service",jsonListService);
                
                paymentMain = paymentMainDAO.readPayment(r);
                
                //Status atual do pagamento via MercadoPago
                if((!paymentMain.getStatus().equals("cancelled")) && (!paymentMain.getStatus().equals("refunded"))){
                    try{
                        //Pega o status anterior    
                        paymentAux = paymentMain.getStatus();
                        //Pega o status atual
                        paymentMain.readPayment(paymentMain.getId());
                        //Se pagamento atual é diferente do anterior

                        if(!paymentAux.equals(paymentMain.getStatus())){
                            //Atualiza Status
                            paymentMainDAO.updatePayment(paymentMain);
                            //Carrega o objeto novamento com as alteraçoes
                            paymentMain = paymentMainDAO.readPayment(r);
                        }
                        
                    }catch(Exception erro){
                        throw new RuntimeException("Consulta: "+ erro);
                    }
                }

                gsonPayment = new StringBuilder(gson.toJson(paymentMain));
                jsonPayment = new JSONObject(gsonPayment.toString());

                jsonResponse.put("payment", jsonPayment);
            
            }catch(JSONException erro){
                throw new RuntimeException(erro);
            }
            
            listResponse.add(jsonResponse);
        }   
        out.println(listResponse);
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
