package netty.tcp2;

/**
 * @Author xuwei
 * @Date 2020/12/27
 * @Version V1.0
 **/
public class MessageProtocol {
    private int length;
    private byte[] content;

    public MessageProtocol() {
    }

    public MessageProtocol(int length, byte[] content) {
        this.length = length;
        this.content = content;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
