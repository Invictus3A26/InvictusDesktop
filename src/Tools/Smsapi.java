/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author LENOVO
 */
public class Smsapi {
    public static final String ACCOUNT_SID = "AC38d1a4e4d5964d4335deb04e8ade8a62";
    public static final String AUTH_TOKEN = "b3e997dd610baa89b9e95ddae11cd708";

    public static void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(/*num ili bch yjih il msg */new PhoneNumber("+21655820931"),new PhoneNumber("+19108125392"), msg).create();

        System.out.println(message.getSid());

    }
}
