package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	

	//필드
	private Socket socket;
	
	//생성자
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	//메소드 gs
	
	
	//메소드 일반
	@Override
	public void run(){
		
		try {
			
			//스트림 선 보강
			System.out.println("[클라이언트가 연결 되었습니다.]");
			
			
			//메세지 받기용 스트림
			InputStream in = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			//메세지보내기용 스트림
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			
			while(true) {
				
				//메세지 받기
				String msg = br.readLine();
				
				if(msg == null) {
					System.out.println("<서버종료>");
					//br.close();
					//bw.close();
					//socket.close();
					break;
				}
				
				System.out.println("받은 메세지: " + msg);
				
				//메세지 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush(); //버퍼가 덜차도 보내는거
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
