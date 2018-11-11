package club.xiehaiyang.lucene;

import java.util.List;

import club.xiehaiyang.entity.Blog;

public interface BlogIndexService {

	public void addIndex(Blog blog) throws Exception;

	public void deleteIndex(String blogId) throws Exception;

	public List<Blog> searchBlog(String q) throws Exception;

	public void updateIndex(Blog blog) throws Exception;

}
