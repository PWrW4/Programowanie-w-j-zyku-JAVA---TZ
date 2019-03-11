
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Exclamation implements ITextProcesing {

    private int inputPort;
    private int outputPort;
    private String outputHost;

    ServerSocket welcomeSocket;


    @Override
    public void setInput(int port) {
        this.inputPort = port;
        System.out.println(inputPort);
    }

    @Override
    public void setOutput(String host, int port) {
        this.outputPort = port;
        this.outputHost = host;

        System.out.println(outputPort + " " + outputHost);
    }

    @Override
    public void start() {
        String clientText = null;

        try {
            welcomeSocket = new ServerSocket(inputPort);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            Socket connectionSocket = null;
            try {
                connectionSocket = welcomeSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("EXL: Ready ");

            DataInputStream inFromClient = null;
            try {
                inFromClient = new DataInputStream(connectionSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                clientText = inFromClient.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("EXL: Recived " + clientText);

            //procesing data
            if (clientText != null) {
                clientText += "!!!";
            }

            Socket outputSocket = null;
            try {
                outputSocket = new Socket("localhost", outputPort);
            } catch (IOException e) {
                e.printStackTrace();
            }
            DataOutputStream outToPrint = null;
            try {
                outToPrint = new DataOutputStream(outputSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //BufferedReader inFromServer=new BufferedReader(new InputStreamReader(outputSocket.getInputStream()));
            //input=inFromUser.readLine();
            try {
                outToPrint.writeUTF(clientText);
            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println("EXL: Final output " + clientText);
        }
    }

    @Override
    public void stop() {
        try {
            welcomeSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
