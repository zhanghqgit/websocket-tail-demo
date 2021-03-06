package com.xxg.websocket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/log/{file}/{num}")
@Slf4j
public class LogWebSocketHandle {
	
	private Process process;
	private InputStream inputStream;
	
	/**
	 * 新的WebSocket请求开启
	 */
	@OnOpen
	public void onOpen(@PathParam("file") String file , @PathParam("num") String num,  Session session) {
		log.info(file);
		file = file.replaceAll("%7C","|").replaceAll("\\|","/");
                log.info(file);
                log.info(num);
//		try {
//			// 执行tail -f命令
//			process = Runtime.getRuntime().exec("tail -f -n " + num + " " + file);
//			inputStream = process.getInputStream();
//
//			// 一定要启动新的线程，防止InputStream阻塞处理WebSocket的线程
//			TailLogThread thread = new TailLogThread(inputStream, session);
//			thread.start();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * WebSocket请求关闭
	 */
	@OnClose
	public void onClose() {
		try {
			if(inputStream != null)
				inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(process != null)
			process.destroy();
	}
	
	@OnError
	public void onError(Throwable thr) {
		thr.printStackTrace();
	}
}
