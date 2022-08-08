package com.sample.netty.codec;

import com.sample.netty.protocol.RequestData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author duyenthai
 */
public class RequestEncoder extends MessageToByteEncoder<RequestData> {

    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    protected void encode(ChannelHandlerContext ctx, RequestData msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getStringValue().length());
        out.writeInt(msg.getIntValue());
        out.writeCharSequence(msg.getStringValue(), charset);
    }
}
