import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.fix44.Logon;
import quickfix.fix44.MessageCracker;
import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.SecurityDefinition;

/**
 * Created by jp on 20.04.16.
 */
public class ConnectionApplication extends MessageCracker
        implements Application {

    @Override
    public void fromAdmin(Message arg0, SessionID arg1) throws FieldNotFound,
            IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println("Successfully called fromAdmin for sessionId : "
                           + arg0);
    }

    @Override
    public void fromApp(Message arg0, SessionID arg1) throws FieldNotFound,
            IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("Successfully called fromApp for sessionId : "
                           + arg0);
    }

    @Override
    public void onCreate(SessionID arg0) {
        System.out.println("Successfully called onCreate for sessionId : "
                           + arg0);
    }

    @Override
    public void onLogon(SessionID arg0) {
        System.out.println("Successfully logged on for sessionId : " + arg0);
    }

    @Override
    public void onLogout(SessionID arg0) {
        System.out.println("Successfully logged out for sessionId : " + arg0);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        System.out.println("Inside toAdmin");
    }

    @Override
    public void toApp(Message arg0, SessionID arg1) throws DoNotSend {
        System.out.println("Message : " + arg0 + " for sessionid : " + arg1);
    }

    @Override
    public void onMessage( NewOrderSingle message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        System.out.println("Inside onMessage for New Order Single");
        super.onMessage(message, sessionID);
    }

    @Override
    public void onMessage( SecurityDefinition message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        System.out.println("Inside onMessage for SecurityDefinition");
        super.onMessage(message, sessionID);
    }

    @Override
    public void onMessage( Logon message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        System.out.println("Inside Logon Message");
        super.onMessage(message, sessionID);
    }
}
