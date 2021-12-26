package nio.timeserver;

public class TimeServer {
    public static void main(String[] args) {
        MultiplexerTimeServer timerServer = new MultiplexerTimeServer(9999);
        timerServer.run();
    }
}
