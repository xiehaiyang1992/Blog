package club.xiehaiyang.controller;

import java.io.PrintStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.telnet.TelnetClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

	/*
	 * ͨ��ģ��telnet�Ĺ�������james���û�
	 * http://www.xiehaiyang.club/email/adduser.html?name=abc&password=1234
	 */
	@RequestMapping(value = "/adduser", produces = "text/html;charset=UTF-8")
	public String addEMailUser(@RequestParam(name = "name") String name,
			@RequestParam(name = "password") String password, HttpServletResponse httpServletRespons) {
		// ʹ��Hbuilder��ʱ��jquery��ʾ����Զ�̵���,ֻ����������
		httpServletRespons.setHeader("Access-Control-Allow-Origin", "*");
		try {
			TelnetClient telnetClient = new TelnetClient("vt200");
			telnetClient.setDefaultTimeout(50000);
			telnetClient.connect("127.0.0.1", 4555);
			PrintStream pStream = new PrintStream(telnetClient.getOutputStream());
			pStream.println("root");
			pStream.flush();
			pStream.println("!changeme!");
			pStream.flush();
			pStream.println("adduser " + name + " " + password);
			pStream.flush();
			pStream.close();
			return "Your new mailbox:" + name + "@xiehaiyang.club";
		} catch (/* IO */Exception e) {
			return "Registration failure";
		}
	}
}
