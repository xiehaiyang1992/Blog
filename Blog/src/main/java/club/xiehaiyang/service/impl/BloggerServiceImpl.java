package club.xiehaiyang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import  club.xiehaiyang.dao.BloggerDao;
import  club.xiehaiyang.entity.Blogger;
import  club.xiehaiyang.service.BloggerService;

/**
 * ����Serviceʵ����
 * @author Administrator
 *
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Resource
	private BloggerDao bloggerDao;

	public Blogger find() {
		return bloggerDao.find();
	}

	public Blogger getByUserName(String userName) {
		return bloggerDao.getByUserName(userName);
	}

	public Integer update(Blogger blogger) {
		return bloggerDao.update(blogger);
	}
	
	
}
