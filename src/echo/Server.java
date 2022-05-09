package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket();
		//serverSocket.bind(new InetSocketAddress("192.168.0.10", 10001)); //(ip, port) //집
		serverSocket.bind(new InetSocketAddress("192.168.0.219", 10002)); //학원
		
		System.out.println("<서버시작>");
		System.out.println("=============================================");
		System.out.println("[연결을 기다리고 있습니다]");
		
		//반복시작==================================================================
		while(true) {
			Socket socket = serverSocket.accept();
			
			if(socket == null) {
				break;
			}
			
			
			//스레드
			//스트림 보강하고 메세지 주고받기
			Thread thread = new ServerThread(socket);
			thread.start();
		}
		//반복 종료========================================================
		
		serverSocket.close();
	}
}
