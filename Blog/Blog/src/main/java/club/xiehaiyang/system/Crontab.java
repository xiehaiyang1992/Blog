package club.xiehaiyang.system;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import club.xiehaiyang.dao.SCpuMapper;
import club.xiehaiyang.dao.SMemMapper;
import club.xiehaiyang.entity.SCpu;
import club.xiehaiyang.entity.SMem;

/*使用定时器做记录，标准单位为5毫秒(0.001秒)*/
@Component
public class Crontab {
	@Autowired
	private SMemMapper sMemMapper;

	@Autowired
	private SCpuMapper sCpuMapper;

	/* fixedRate表示初次启动后 */
	@Scheduled(cron = "0/5 * * * * ?")
	public void recodeMemory() {
		// 自动记录服务器内存百分百占用(windows是开发机器不算)
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") < 0) {
			try {
				Sigar sigar = new Sigar();
				Mem mem = sigar.getMem();
				CpuPerc cpuList[] = sigar.getCpuPercList();
				//原本以为服务器的cpu是单核双线程，想多了，其实只有单线程
				sCpuMapper.insert2Cpu(new SCpu(String.valueOf(cpuList[0].getCombined()), null));
				sMemMapper.insert(new SMem(String.valueOf(mem.getUsed() * 1.0 / (1024 * 1024 * 1024.0)),
						String.valueOf(mem.getTotal() * 1.0 / (1024 * 1024 * 1024.0))));
			} catch (SigarException e) {
				e.printStackTrace();
			}
		}
	}
}
