/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
    import com.mercadopago.MP;
    import com.mercadopago.MercadoPago;
    import com.mercadopago.exceptions.MPException;
    import com.mercadopago.resources.Payment;
    import com.mercadopago.resources.datastructures.payment.Payer;
    import java.io.IOException;
    import java.io.UnsupportedEncodingException;
    import org.apache.http.HttpEntity;
    import org.apache.http.HttpResponse;
    import org.apache.http.client.ClientProtocolException;
    import org.apache.http.client.ResponseHandler;
    import org.apache.http.client.methods.HttpPost;
    import org.apache.http.client.methods.HttpPut;
    import org.apache.http.client.methods.HttpRequestBase;
    import org.apache.http.entity.StringEntity;
    import org.apache.http.impl.client.CloseableHttpClient;
    import org.apache.http.impl.client.HttpClients;
    import org.apache.http.util.EntityUtils;
    import org.codehaus.jettison.json.JSONException;
    import org.codehaus.jettison.json.JSONObject;
/**
 *
 * @author GRAFIX
 */
public class PaymentMain {
    private String id;
    private long refundId;
    private int responseCode;
    private String keyRefund;
    private Client client;
    private Running running;
    private String status;    
    private float amount;
    private String statusDetail;
    private String description;
    private String flagCard;
    //private static final String ACCESSTOKEN = "TEST-6283791763683511-021519-ff28c21cbcba1d12ea4ee085289730aa-5346060";
    private static final String ACCESSTOKEN = "TEST-2788051065426862-031818-36eb1692241450569c550367bdeeb693-221071378";
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }  
    
    public long getRefundId(){
        return refundId;
    }
    
    public int getResponseCode(){
        return responseCode;
    }
    
    public String getKeyRefund() {
        return keyRefund;
    }

    public void setKeyRefund(String keyRefund) {
        this.keyRefund = keyRefund;
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Running getRunning() {
        return running;
    }

    public void setRunning(Running running) {
        this.running = running;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public float getAmount() {
        return amount;
    }
    
    public void setAmount(Float amount){
        this.amount = amount;
    }
    
    public String getStatusDetail() {
        return statusDetail;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getFlagCard(){
        return flagCard;
    }
    
    public void setFlagCard(String flagCard){
        this.flagCard = flagCard;
    }
    
    private void setResponse(HttpRequestBase http, int refund) throws JSONException{   
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String response = "";
        
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                /* Get status code */
                int httpResponseCode = httpResponse.getStatusLine().getStatusCode();
                responseCode = httpResponseCode;
                if (httpResponseCode >= 200 && httpResponseCode < 300) {
                    /* Convert response to String */
                    HttpEntity entity = httpResponse.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    return null;
                }
                }
            };
            
             try {
                response = httpClient.execute(http, responseHandler);
                
                JSONObject jsonObject = new JSONObject(response);
                switch (refund){
                    case 1:
                        this.id = jsonObject.getString("id");
                        this.status = jsonObject.getString("status");
                        break;
                    case 2:
                        this.refundId = jsonObject.getInt("id");
                        break;   
                    default:
                        break;
                }
                System.out.println("Response: " + response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
    }
      
    private void setResponse(String response) throws JSONException{
        JSONObject responseJson = new JSONObject(response);
        this.id = responseJson.getString("id");
        this.statusDetail = responseJson.getString("status_detail");
     
        if(statusDetail.equals("partially_refunded")){
            this.status = "refunded";
        }else{
            this.status = responseJson.getString("status");
        }
    }
    
    public void createPayment(float amount, String token, String flagCard, String email) throws UnsupportedEncodingException, JSONException, Exception{     
        email = "test_user_19653727@testuser.com";
        MercadoPago.SDK.setAccessToken(ACCESSTOKEN);
        Payment payment = new Payment();
        payment.setTransactionAmount(amount).setToken(token).setDescription("MirandaTaxi").setInstallments(1).setPaymentMethodId(flagCard).setPayer(new Payer().setEmail(email));
        payment.save();
        
        try{
            this.id = payment.getId();
            this.status = String.valueOf(payment.getStatus());
            this.description = payment.getStatementDescriptor();
            this.flagCard = payment.getPaymentMethodId();
        }catch(Exception erro){
            this.status = "null";
            throw new RuntimeException(erro);
        } 
    }
    
    
    public void readPayment(String id) throws JSONException, UnsupportedEncodingException, Exception{
        MP mp = new MP(ACCESSTOKEN);
        JSONObject payment = mp.get("/v1/payments/"+id);
        setResponse(payment.getString("response"));
    }
    
    public void cancelPayment(String id) throws UnsupportedEncodingException, JSONException, IOException, MPException{
        Payment payment = Payment.findById(id);
        payment.setStatus(Payment.Status.cancelled);
        payment.update();
    }
    
    public void refundPayment(String id) throws JSONException{
        HttpPost httpPost = new HttpPost("https://api.mercadopago.com/v1/payments/"+id+"/refunds?access_token="+ACCESSTOKEN);
        httpPost.addHeader("X-Idempotency-Key",getKeyRefund());
        httpPost.addHeader("Content-Type","application/json");
        setResponse(httpPost,2);
    }
    
    public void refundHalfPayment(String id, float amount) throws JSONException, UnsupportedEncodingException{
        HttpPost httpPost = new HttpPost("https://api.mercadopago.com/v1/payments/"+id+"/refunds?access_token="+ACCESSTOKEN);
        httpPost.addHeader("X-Idempotency-Key",getKeyRefund());
        httpPost.addHeader("Content-Type","application/json");
        StringEntity stringAmount = new StringEntity("{\"amount\":"+amount+"}");
        httpPost.setEntity(stringAmount);
        setResponse(httpPost,2);
    } 
}
