import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.FileLogFactory;
import quickfix.FileStoreFactory;
import quickfix.Message;
import quickfix.MessageFactory;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;
import quickfix.field.BeginString;
import quickfix.field.HeartBtInt;
import quickfix.field.ResetSeqNumFlag;
import quickfix.fix44.Logon;

/**
 * Created by jp on 20.04.16.
 */
public class Initiator {

    public static void main(String[] args) {
        SocketInitiator socketInitiator = null;
        try {
            SessionSettings sessionSettings = new SessionSettings( "/home/jp/macd/workspace/SimpleFixApplication/sessionInitiatorSettings.txt");
            Application application = new ConnectionApplication();
            FileStoreFactory fileStoreFactory = new FileStoreFactory( sessionSettings);
            FileLogFactory logFactory = new FileLogFactory( sessionSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            socketInitiator = new SocketInitiator(application,
                                                  fileStoreFactory, sessionSettings, logFactory,
                                                  messageFactory);
            socketInitiator.start();
            SessionID sessionId = socketInitiator.getSessions().get( 0);
            sendLogonRequest(sessionId);
            int i = 0;
            do {
                try {
                    Thread.sleep(1000000000);
                    System.out.println(socketInitiator.isLoggedOn());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            } while ((!socketInitiator.isLoggedOn()) && (i < 30));
        } catch ( ConfigError e) {
            e.printStackTrace();
        } catch ( SessionNotFound e) {
            e.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
    private static void sendLogonRequest(SessionID sessionId)
            throws SessionNotFound {
        Logon logon = new Logon();
        Message.Header header = logon.getHeader();
        header.setField(new BeginString( "FIX.4.4"));
        logon.set(new HeartBtInt( 30));
        logon.set(new ResetSeqNumFlag( true));
        boolean sent = Session.sendToTarget( logon, sessionId);
        System.out.println("Logon Message Sent : " + sent);
    }

}
