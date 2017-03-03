import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.FileLogFactory;
import quickfix.FileStoreFactory;
import quickfix.MessageFactory;
import quickfix.SessionSettings;
import quickfix.SocketAcceptor;

/**
 * Created by jp on 21.04.16.
 */
public class Acceptor {


    public static void main(String[] args) {
        SocketAcceptor socketAcceptor = null;
        try {
            SessionSettings sessionSettings = new SessionSettings( "/home/jp/macd/workspace/SimpleFixApplication/sessionAcceptorSettings.txt");
            Application application = new AcceptorApplication(sessionSettings);
            FileStoreFactory fileStoreFactory = new FileStoreFactory( sessionSettings);
            FileLogFactory logFactory = new FileLogFactory( sessionSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            socketAcceptor = new SocketAcceptor(application,
                                                  fileStoreFactory, sessionSettings, logFactory,
                                                  messageFactory);
            socketAcceptor.start();
        } catch ( ConfigError e) {
            e.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            if (socketAcceptor != null) {
                socketAcceptor.stop(true);
            }
        }
    }

}
