package netty.http2;

import com.sun.org.apache.regexp.internal.RE;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {



    private String sanitizeUri(String uri) {
        try {
            uri = URLDecoder.decode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            try {
                uri = URLDecoder.decode(uri, "ISO-8859-1");
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
            }
        }

        uri = uri.replace('/', File.separatorChar);
        if (uri.contains(File.separator + ".")
                || uri.contains("." + File.separator) || uri.startsWith(".")
                || uri.endsWith(".")) {
            return null;
        }

        return System.getProperty("user.dir") + File.separator + uri;
    }
    private static final Pattern ALLOWED_FILE_NAME = Pattern.compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

    }
}
