package club.xiehaiyang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import  club.xiehaiyang.dao.CommentDao;
import  club.xiehaiyang.entity.Comment;
import  club.xiehaiyang.service.CommentService;

/**
 * ����Serviceʵ����
 * @author Administrator
 *
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Resource
	private CommentDao commentDao;
	
	public int add(Comment comment) {
		return commentDao.add(comment);
	}

	public List<Comment> list(Map<String, Object> map) {
		return commentDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return commentDao.getTotal(map);
	}

	public Integer delete(Integer id) {
		return commentDao.delete(id);
	}

	public int update(Comment comment) {
		return commentDao.update(comment);
	}

}
