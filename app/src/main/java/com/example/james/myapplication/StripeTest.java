//package com.example.james.myapplication;
//
//import java.io.UnsupportedEncodingException;
//
//import com.stripe.model.Charge;
//import com.stripe.util.StripeClient;
//import org.apache.commons.codec.binary.Base64;
//
//public class StripeTest {
//
//    public static String base64(String in) throws UnsupportedEncodingException {
//        return new String(Base64.encodeBase64(in.getBytes("utf-8")));
//    }
//
//    public static void main(String[] args) {
//        try {
//            StripeClient.setKey(base64("your-api-key"));
//        } catch (UnsupportedEncodingException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        try {
//            Charge c = StripeClient.newCharge(100, "usd", "4242424242424242", "12", "2012", null, null, null, null, null, null, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}