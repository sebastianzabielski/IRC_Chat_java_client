import dataStructures.Receive;
import exceptions.ReadonlyAttributeException;
import exceptions.ReconnectionAttemptFailedException;
import statics.ApiStatics;

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

    protected Receive receive() throws IOException, ReadonlyAttributeException {
        String header = this.receive(ApiStatics.IOBlockSeparator);
        Receive receive = new Receive(header);

        Integer bodyLen = receive.getConLen();
        String body = this.receive(bodyLen);
        receive.setBody(body);

        return receive;

    }

    protected String receive(int len) throws IOException {
        StringBuilder result = new StringBuilder();
        while (result.length() < len) {
            char a = (char) this.input.read();
            result.append(a);
        }
        return result.toString();
    }

    protected String receive(String searchSequence) throws IOException {
        StringBuilder result = new StringBuilder();
        while (!result.toString().endsWith(searchSequence)) {
            char a = (char) this.input.read();
            result.append(a);
        }
        return result.toString();
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
