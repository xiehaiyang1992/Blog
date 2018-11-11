package club.xiehaiyang.controller;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import club.xiehaiyang.dao.SCpuMapper;
import club.xiehaiyang.dao.SMemMapper;
import club.xiehaiyang.entity.SCpu;
import club.xiehaiyang.entity.SMem;

@Controller
@RequestMapping("/system")
public class SystemInfoController {
	@Autowired
	private SCpuMapper sCpuMapper;

	@Autowired
	private SMemMapper sMemMapper;

	@RequestMapping(value = "/cpu")
	@ResponseBody
	public String getCpuInfo(@RequestParam(name = "scope", required = false) String scope,
			HttpServletResponse httpServletRespons) throws Exception {
		httpServletRespons.setHeader("Access-Control-Allow-Origin", "*");
		ObjectMapper objectMapper = new ObjectMapper();
		if ("5min".equals(scope)) {
			return objectMapper.writeValueAsString(sCpuMapper.selectCpuUseInfo5min(new SCpu()));
		}
		if ("1day".equals(scope)) {
			return objectMapper.writeValueAsString(sCpuMapper.selectCpuUseInfo1day(new SCpu()));
		} else {// 默认就为一周
			return objectMapper.writeValueAsString(sCpuMapper.selectCpuUseInfo1week(new SCpu()));
		}

	}

	@RequestMapping(value = "/mem")
	@ResponseBody
	public String getMemInfo(@RequestParam(name = "scope", required = false) String scope,
			HttpServletResponse httpServletRespons) throws Exception {
		httpServletRespons.setHeader("Access-Control-Allow-Origin", "*");
		if ("5min".equals(scope)) {
			return new ObjectMapper().writeValueAsString(sMemMapper.selectMemUseInfo5min(new SMem()));
		}
		if ("1day".equals(scope)) {
			return new ObjectMapper().writeValueAsString(sMemMapper.selectMemUseInfo1day(new SMem()));
		} else {
			return new ObjectMapper().writeValueAsString(sMemMapper.selectMemUseInfo1week(new SMem()));
		}

	}
}
