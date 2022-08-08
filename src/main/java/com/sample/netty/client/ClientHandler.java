package com.sample.netty.client;

import com.sample.netty.protocol.RequestData;
import com.sample.netty.protocol.ResponseData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.log4j.Log4j;

/**
 * @author duyenthai
 */
@Log4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RequestData requestData = new RequestData();
        requestData.setIntValue(123);
        requestData.setStringValue("no play make ok ?");
        ctx.writeAndFlush(requestData);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ResponseData res = null;
        if (msg instanceof ResponseData) {
            res = (ResponseData) msg;
        }
        try {
            log.info(String.format("ctx------------------------%s-----------------res--------------------", ctx));
            log.info(res);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
