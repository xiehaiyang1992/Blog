package club.xiehaiyang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import club.xiehaiyang.service.SpeakService;

@RestController
@RequestMapping("/speak")
public class SpeakController {

	@Resource
	private SpeakService speakServiceImpl;

	// 127.0.0.1/speak/baidu.html?text=1234
	@RequestMapping(value = "/baidu", produces = "text/html;charset=UTF-8")
	public String baidu(@RequestParam(name = "text") String text, HttpServletResponse httpServletRespons) {
		httpServletRespons.setHeader("Access-Control-Allow-Origin", "*");
		return speakServiceImpl.baidu(text);
	}

	// 127.0.0.1/speak/pinyin.html?text=我是谁
	@RequestMapping(value = "/pinyin", produces = "text/html;charset=UTF-8")
	public String pinyin(@RequestParam(name = "text") String text, HttpServletResponse httpServletRespons) {
		httpServletRespons.setHeader("Access-Control-Allow-Origin", "*");
		try {
			text = PinyinHelper.convertToPinyinString(text, " ", PinyinFormat.WITH_TONE_MARK).replaceAll(" ", "");
			System.out.println(text);
		} catch (PinyinException e) {
			e.printStackTrace();
		}
		return speakServiceImpl.pinyin(text);
	}

	// 127.0.0.1/speak/centos_zh.html?text=我是谁
	@RequestMapping(value = "/centos_zh", produces = "text/html;charset=UTF-8")
	public String centos_zh(@RequestParam(name = "text") String text, HttpServletResponse httpServletRespons) {
		httpServletRespons.setHeader("Access-Control-Allow-Origin", "*");
		return speakServiceImpl.centos_zh(text);
	}

	// 127.0.0.1/speak/centos_zhy.html?text=我是谁
	@RequestMapping(value = "/centos_zhy", produces = "text/html;charset=UTF-8")
	public String centos_zhy(@RequestParam(name = "text") String text, HttpServletResponse httpServletRespons) {
		httpServletRespons.setHeader("Access-Control-Allow-Origin", "*");
		return speakServiceImpl.centos_zhy(text);
	}
}
