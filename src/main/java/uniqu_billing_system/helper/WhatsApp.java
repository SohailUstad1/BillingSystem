package uniqu_billing_system.helper;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class WhatsApp {
	public static boolean send(String in){
		boolean b=false;
		try {
			String asid = "AC8c26dd7462e4aaf587837b6fd8239b96";
			String atkn = "a56d6099c45a9b5d8bb2f86bf07ec7c2";
			String myNumber = "whatsapp:+14155238886";
			String toNumber = "whatsapp:+918898542558";
//	        String mess="Hello Sohail, This is Sohail sending message using java and twilio";
			Twilio.init(asid, atkn);
			Message message = Message
					.creator(new PhoneNumber(toNumber), new PhoneNumber(myNumber), in)
					.create();
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return b;
	}
	public static void main(String[] args) {
		boolean send = send("hello");
		System.out.println(send);
	}
}
