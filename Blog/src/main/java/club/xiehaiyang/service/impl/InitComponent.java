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
 * 初始化组件 把博主信息 根据博客类别分类信息 根据日期归档分类信息 存放到application中，用以提供页面请求性能
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
		Blogger blogger = bloggerService.find(); // 查询博主信息
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);

		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
		List<BlogType> blogTypeCountList = blogTypeService.countList(); // 查询博客类别以及博客的数量
		application.setAttribute("blogTypeCountList", blogTypeCountList);

		BlogService blogService = (BlogService) applicationContext.getBean("blogService");
		List<Blog> blogCountList = blogService.countList(); // 根据日期分组查询博客
		application.setAttribute("blogCountList", blogCountList);

		LinkService linkService = (LinkService) applicationContext.getBean("linkService");
		List<Link> linkList = linkService.list(null); // 查询所有的友情链接信息
		application.setAttribute("linkList", linkList);

		// 删除旧的,生成新的lucene信息
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

	// 删除索引文件的做法
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
