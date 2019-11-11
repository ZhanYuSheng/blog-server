package com.eatshit.bolg.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@ServerEndpoint("/websocket")
@Component
@Slf4j
public class WebSocketServer {
    // 存储所有在线的socket连接
    private static Map<String, WebSocketServer> webSocketMap = new LinkedHashMap<>();
    // 当前在线数目
    private static int count = 0;
    // 会话连接，websocket连入时会创建一个WebSocketServer实例
    private Session session;

    // 连接建立
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketMap.put(session.getId(), this);
        addCount();
        log.info("新的连接加入：{}", session.getId());
    }

    // 接收消息
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到客户端{}消息：{}", session.getId(), message);
        try {
            this.sendMessage("收到消息：" + message);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    // 错误处理
    @OnError
    public void onError(Throwable error, Session session) {
        log.info("发生错误{},{}", session.getId(), error.getMessage());
    }

    // 关闭连接
    @OnClose
    public void onClose() {
        webSocketMap.remove(this.session.getId());
        reduceCount();
        log.info("连接关闭:{}", this.session.getId());
    }

    // 发送消息
    public void sendMessage(String message)
            throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息，当没有sid时则群发
     */
    public static void sendInfo(String message, @PathParam("sid") String sid)
            throws IOException {
        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
        WebSocketServer.webSocketMap.forEach((k, v) -> {
            try {
                // 这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    v.sendMessage(message);
                } else if (k.equals(sid)) {
                    v.sendMessage(message);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }


    // 广播消息(群发)
    public static void broadcast(String message) {
        WebSocketServer.webSocketMap.forEach((k, v) -> {
            try {
                v.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 获取在线连接数目
    public static int getCount() {
        return count;
    }

    // 操作count，使用synchronized确保线程安全
    public static synchronized void addCount() {
        WebSocketServer.count++;
    }

    public static synchronized void reduceCount() {
        WebSocketServer.count--;
    }

}
