package com.example.websocket;

import java.net.InetSocketAddress;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.domain.User;

@Service
public class WebScoket implements ApplicationListener<ApplicationEvent>{
	private Logger logger = LoggerFactory.getLogger(WebScoket.class);
	 /**socket服务器域名*/
    private volatile String hostName;
    /**socket服务器port*/
    private volatile Integer serverPort;
    /**socket服务器*/
    private volatile SocketIOServer server = null;
    private boolean isStarted = false;
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public Integer getServerPort() {
		return serverPort;
	}
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
	@PostConstruct
	public void init() {
        logger.info("websockt启动");
        Configuration config = new Configuration();
        configFromPropety(config);
        this.server = new SocketIOServer(config);
        this.addEventListener();
    }
	  /**
     * 启动服务
     * @return
     */
    public boolean startServer(){
        if(isStarted){
            setStarted();
            return true;
        }

        try {
            this.server.start();
            setStarted();
            return true;
        } catch (Exception e) {
            logger.error("error={}",e);
            return false;
        }
    }
    private void setStarted(){
        if(!isStarted){
            isStarted=true;
        }
    }

    private void setStop(){
        if(isStarted){
            isStarted=false;
        }
    }
    /**
     * 关闭服务
     * @return
     */
    public boolean stopServer(){
        if(isStarted){
            try {
                this.server.stop();
                setStop();
                return true;
            } catch (Exception e) {
                logger.error("error={}",e);
                return false;
            }
        }else{
            setStop();
            return false;
        }
    }
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextClosedEvent ){
            logger.info("关闭socket开始");
            this.stopServer();
            logger.info("关闭socket结束");
        }else if(event instanceof ContextRefreshedEvent ){
            logger.info("启动socket开始");
            this.startServer();
            logger.info("启动socket结束");
        }
    }
	private void configFromPropety(Configuration config) {
	    try{
//	        String hostName = InetAddress.getLocalHost().getHostAddress();
	        String hostName = "169.254.215.27";
	        String serverPort = "10443";
	        logger.info("websocket hostName={},serverPort={}",hostName,serverPort);
	        if (hostName != null) {
		        this.setHostName(hostName);
	            config.setHostname(hostName);
	        }
	        if (serverPort != null) {
		        this.setServerPort(Integer.valueOf(serverPort));
	            config.setPort(Integer.valueOf(serverPort));
	        }
	    } catch (Exception e) {
	        logger.error("error={}",e);
	      }
	    }
	
	private void addEventListener() {
		 //客户端连接时缓存客户端信息
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                UUID sessionId = client.getSessionId();
                InetSocketAddress remoteAddress =  (InetSocketAddress)client.getRemoteAddress();
                logger.info("onConnect client sessionId = {},remoteAddress={}",sessionId,JSON.toJSON(remoteAddress));
            }
        });

        //客户端断开是去除缓存信息
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                UUID sessionId = client.getSessionId();
                InetSocketAddress remoteAddress =  (InetSocketAddress)client.getRemoteAddress();
                logger.info("onDisconnect client sessionId = {},remoteAddress={}",sessionId,JSON.toJSON(remoteAddress));
            }
        });
        this.push();
	}
	
	private void push() {
		//获取文件落成状态事件
        server.addEventListener("getFileFlagEvent", User.class, new DataListener<User>() {
            @Override
            public void onData(SocketIOClient client, User data, AckRequest ackRequest) {
                UUID sessionId = client.getSessionId();
                logger.info("getFileFlagEvent client sessionId = {}",sessionId);
                logger.info("getFileFlagEvent client User = {}",JSON.toJSON(data));
                client.sendEvent("getFileFlagEvent1", data);
            }
        });
	}

}
