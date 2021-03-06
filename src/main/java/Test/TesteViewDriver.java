/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

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
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author eduan
 */
public class TesteViewDriver {
    public static void main(String args[]){
        
        Client client = new Client();
        Running running = new Running();
        ClassCar classCar = new ClassCar();
        
        //Test search
        client.setName("Eduan Lima");
        classCar.setId("ftc");
        running.setClassCar(classCar);
        running.setNamePassenger("none");
        running.setData("none");
        running.setId(0);
   
        List<Running> listRunning = null;
        
        RunningDAO runningDAO = new RunningDAO();
        
        listRunning = runningDAO.readRunning(client, running);
        
        List<ItemService> listItemService;
        ItemServiceDAO itemServiceDAO = new ItemServiceDAO();
        
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
                
                //Se a corrida ainda não foi finalizada 
                if(r.getStatus() == 1){
                        //Status atual do pagamento via MercadoPago
                        if((!paymentMain.getStatus().equals("cancelled")) && (!paymentMain.getStatus().equals("refunded"))){
                            System.out.println("passei");
                            try{
                                //Pega o status anterior    
                                paymentAux = paymentMain.getStatus();
                                //Pega o status atual
                                paymentMain.readPayment(paymentMain.getId());
                                //Se pagamento atual é diferente do anterior
                                //auxAP = auxAP.concat(" "+paymentMain.getStatus());

                                if(!paymentAux.equals(paymentMain.getStatus())){
                                    //Atualiza Status
                                    paymentMainDAO.updatePayment(paymentMain);
                                    //Carrega o objeto novamento com as alteraçoes
                                    paymentMain = paymentMainDAO.readPayment(r);
                                }
                            }catch(Exception erro){
                                throw new RuntimeException("Consulta: "+ erro);
                            }
                        }else{
                            //auxCP = auxCP.concat(" "+paymentMain.getStatus());
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
        
        System.out.println(listResponse);
    }
}
