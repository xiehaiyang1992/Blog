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
 *天气接口调用，因为https链接中调用http的链接会因为无法认证ssl导致页面无法加载的问题
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
			 result=IOUtils.toString(url.openConnection().getInputStream(), "UTF-8").replace("七日天气", "天气");
			//去掉头部的缓存
			result=result.replace(" manifest=\"/index.appcache\"","");
			//删除跳转链接
			result=result.replace("href=\"http://huizhou.tianqi.com/?tq\"", "");
			//删除百度统计链接
			result=result.replace(result.substring(result.indexOf("<div style=\"display:none\""), result.indexOf("</script></div>")+15),"");
			//删除兼容
			result=result.replace(result.substring(result.indexOf("<!--[if IE 6]>"), result.indexOf("<![endif]-->")+15),"");
			

			//目录替换
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