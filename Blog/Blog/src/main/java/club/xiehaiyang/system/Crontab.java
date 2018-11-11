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

/*ʹ�ö�ʱ������¼����׼��λΪ5����(0.001��)*/
@Component
public class Crontab {
	@Autowired
	private SMemMapper sMemMapper;

	@Autowired
	private SCpuMapper sCpuMapper;

	/* fixedRate��ʾ���������� */
	@Scheduled(cron = "0/5 * * * * ?")
	public void recodeMemory() {
		// �Զ���¼�������ڴ�ٷְ�ռ��(windows�ǿ�����������)
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") < 0) {
			try {
				Sigar sigar = new Sigar();
				Mem mem = sigar.getMem();
				CpuPerc cpuList[] = sigar.getCpuPercList();
				//ԭ����Ϊ��������cpu�ǵ���˫�̣߳�����ˣ���ʵֻ�е��߳�
				sCpuMapper.insert2Cpu(new SCpu(String.valueOf(cpuList[0].getCombined()), null));
				sMemMapper.insert(new SMem(String.valueOf(mem.getUsed() * 1.0 / (1024 * 1024 * 1024.0)),
						String.valueOf(mem.getTotal() * 1.0 / (1024 * 1024 * 1024.0))));
			} catch (SigarException e) {
				e.printStackTrace();
			}
		}
	}
}
