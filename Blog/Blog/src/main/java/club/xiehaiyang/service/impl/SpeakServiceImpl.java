package club.xiehaiyang.service.impl;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.stereotype.Service;

import club.xiehaiyang.service.SpeakService;

@Service
public class SpeakServiceImpl implements SpeakService {
	/*
	 * 获取当前WEB项目的绝对路径
	 */
	public String path = "/"
			+ this.getClass().getResource("/").getPath().replaceFirst("/", "").replaceAll("WEB-INF/classes/", "")
			+ "static/media/";

	public String baidu(String text) {
		try {
			URL url = new URL(
					"http://fanyi.baidu.com/gettts?lan=zh&spd=5&source=web&text=" + URLEncoder.encode(text, "utf-8"));
			DataInputStream dis = new DataInputStream(url.openStream());
			String imageName = new Date().getTime() + ".mp3";
			FileOutputStream fos = new FileOutputStream(new File(path + imageName));
			byte[] buffer = new byte[1024];
			int length;
			while ((length = dis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
			dis.close();
			fos.close();
			return imageName;
			// return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	// espeak -v zh -w 123.wav "我在哪里"
	public String centos_zh(String text) {
		try {
			String imageName = new Date().getTime() + ".wav";
			Runtime.getRuntime().exec("espeak -v zh -w " + path + imageName + " \"" + text + "\"").getInputStream();
			return imageName;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String centos_zhy(String text) {
		try {
			String imageName = new Date().getTime() + ".wav";
			Runtime.getRuntime().exec("espeak -v zhy -w " + path + imageName + " \"" + text + "\"").getInputStream();
			return imageName;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String pinyin(String text) {
		try {
			String imageName = new Date().getTime() + ".wav";
			Runtime.getRuntime().exec("espeak -v zh -w " + path + imageName + " \"" + text + "\"").getInputStream();
			return imageName;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
