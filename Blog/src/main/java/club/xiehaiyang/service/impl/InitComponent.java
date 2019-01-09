package club.xiehaiyang.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import club.xiehaiyang.entity.Blog;
import club.xiehaiyang.entity.BlogType;
import club.xiehaiyang.entity.Blogger;
import club.xiehaiyang.entity.Link;
import club.xiehaiyang.lucene.BlogIndex;
import club.xiehaiyang.service.BlogService;
import club.xiehaiyang.service.BlogTypeService;
import club.xiehaiyang.service.BloggerService;
import club.xiehaiyang.service.LinkService;

/**
 * ��ʼ����� �Ѳ�����Ϣ ���ݲ�����������Ϣ �������ڹ鵵������Ϣ ��ŵ�application�У������ṩҳ����������
 * 
 * @author Administrator
 *
 */
@Component
public class InitComponent implements ServletContextListener, ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		InitComponent.applicationContext = applicationContext;
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext application = servletContextEvent.getServletContext();
		BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerService");
		Blogger blogger = bloggerService.find(); // ��ѯ������Ϣ
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);

		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
		List<BlogType> blogTypeCountList = blogTypeService.countList(); // ��ѯ��������Լ����͵�����
		application.setAttribute("blogTypeCountList", blogTypeCountList);

		BlogService blogService = (BlogService) applicationContext.getBean("blogService");
		List<Blog> blogCountList = blogService.countList(); // �������ڷ����ѯ����
		application.setAttribute("blogCountList", blogCountList);

		LinkService linkService = (LinkService) applicationContext.getBean("linkService");
		List<Link> linkList = linkService.list(null); // ��ѯ���е�����������Ϣ
		application.setAttribute("linkList", linkList);

		// ɾ���ɵ�,�����µ�lucene��Ϣ
		BlogIndex blogIndex = new BlogIndex();
		File file = new File(blogIndex.getPath());
		deleteFolder(file);
		for (Blog blog : blogService.list(new HashMap<String, Object>())) {
			System.out.println(blog.toString());
			try {
				blog.setContentNoTag(blog.getContent().replaceAll("<.*?>", "").replaceAll("&nbsp;", ""));
				blogIndex.addIndex(blog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ɾ�������ļ�������
	public static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

}
