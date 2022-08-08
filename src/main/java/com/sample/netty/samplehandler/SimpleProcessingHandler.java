package com.sample.netty.samplehandler;

import com.sample.netty.protocol.RequestData;
import com.sample.netty.protocol.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.log4j.Log4j;

/**
 * @author duyenthai
 */
@Log4j
public class SimpleProcessingHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf temp;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf message = (ByteBuf) msg;
        temp.writeBytes(message);
        message.release();
        if (temp.readableBytes() >= 4) {
            RequestData requestData = new RequestData();
            requestData.setIntValue(temp.readInt());
            ResponseData responseData = new ResponseData();
            requestData.setIntValue(requestData.getIntValue() * 2);
            ChannelFuture future = ctx.writeAndFlush(responseData);
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info(String.format("Added handler for context %s", ctx));
        temp = ctx.alloc().buffer();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info(String.format("Handle is removed %s ", ctx));
        temp.release();
        temp = null;
    }
}
