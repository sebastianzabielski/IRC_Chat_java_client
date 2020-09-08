import dataStructures.Receive;
import exceptions.ReadonlyAttributeException;

import java.io.IOException;

public final class Client {
    private static Client instance;
    private Connection connection;

    public Client(String ip, int port) throws IOException {
        if(this.connection == null) {
            this.connection = new Connection(ip, port);
            //TODO run listenReceive on another thread

        }
    }

    private void listenReceive() {
        while(true) {
            try {
                Receive receive = this.connection.receive();
                //TODO emit receive value
            } catch (IOException e) {
                System.out.println("ioexception");
                //TODO need to find a way to clear buffer when pat of data is wrong
                //maby send to backend for recerate message, and clear buffer
                e.printStackTrace();
            } catch (ReadonlyAttributeException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        //TODO stop thread

        super.finalize();
    }
}
