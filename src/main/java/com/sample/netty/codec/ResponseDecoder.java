package com.sample.netty.codec;

import com.sample.netty.protocol.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author duyenthai
 */
public class ResponseDecoder extends ReplayingDecoder<ResponseData> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ResponseData response = new ResponseData();
        response.setIntValue(in.readInt());
        out.add(response);
    }
}
