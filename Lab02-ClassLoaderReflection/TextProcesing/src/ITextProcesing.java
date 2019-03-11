public interface ITextProcesing {
    void setInput(int port);
    void setOutput(String host, int port);
    void start();
    void stop();
}
