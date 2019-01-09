package club.xiehaiyang.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @xiehaiyang
 *�����ӿڵ��ã���Ϊhttps�����е���http�����ӻ���Ϊ�޷���֤ssl����ҳ���޷����ص�����
 *http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=5&py=huizhou
 * */
@Controller
@RequestMapping("/")
public class WeatherController {

	@RequestMapping(value = "/weathe", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String weathe() {
		try {
			URL url = new URL("http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=3&py=huizhou");
			String result;
			 result=IOUtils.toString(url.openConnection().getInputStream(), "UTF-8").replace("��������", "����");
			//ȥ��ͷ���Ļ���
			result=result.replace(" manifest=\"/index.appcache\"","");
			//ɾ����ת����
			result=result.replace("href=\"http://huizhou.tianqi.com/?tq\"", "");
			//ɾ���ٶ�ͳ������
			result=result.replace(result.substring(result.indexOf("<div style=\"display:none\""), result.indexOf("</script></div>")+15),"");
			//ɾ������
			result=result.replace(result.substring(result.indexOf("<!--[if IE 6]>"), result.indexOf("<![endif]-->")+15),"");
			

			//Ŀ¼�滻
			result=result.replace(" href=\"http://www.tianqi.com/data/html/code_city.php\"", "");
			result=result.replace("http://img.tianqi.com", ".");
			result=result.replace("/tianqibig/","/tqicon1/");
			result=result.replace("https://imgs.tianqi.com/static/css/mobile.css","./static/images/tqicon1/mobile.css");
			result=result.replace("https://imgs.tianqi.com/static/js/jquery-1.8.2.min.js","https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js");
			result=result.replace("tqicon1","tianqi");
			return result;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}

/* 127.0.0.1/weathe.html?py=huizhou */