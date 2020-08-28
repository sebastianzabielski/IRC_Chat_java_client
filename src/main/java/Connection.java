import dataStructures.Receive;
import error.ReconnectionAttemptFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection {
    private final String ip;
    private final int port;
    private Socket socket;
    DataOutputStream output;
    BufferedReader input;

    Connection(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
        this.createSocket(ip, port);
    }


    private void createSocket(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
        this.output = new DataOutputStream(this.socket.getOutputStream());
        this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    protected Receive receive() {
        throw new NotImplementedException();
    }

    protected String receive(int len) {
        throw new NotImplementedException();
    }

    protected String receive(String searchSequence) {
        throw new NotImplementedException();
    }


    public void reconnect() throws ReconnectionAttemptFailedException {
        try {
            this.createSocket(this.ip, this.port);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ReconnectionAttemptFailedException();
        }
    }

    public void closeSocket() throws IOException {
        this.socket.close();
    }

}
