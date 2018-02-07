package com.it.dao;

import java.util.List;

import com.it.entity.Comment;
/**
 * 
 * @author Administrator 2017年8月14日 下午2:01:56
 *
 */
public interface CommentDao extends IDao<Comment>{
	/**
	 * 下午2:04:48
	 * 站内信息阅读与否的状态的改变
	 * @param status
	 * @return
	 *
	 */
	public int changStatus(int status);

	/**
	 * 2017年8月18日下午6:54:31 
	 * @param fangDongId
	 * @param userId
	 *	查询会话
	 * @return 
	 */
	public List<Comment> findById2(Integer fangDongId, Integer userId);
	
	
	/**查询回话列表
	 * 2017年8月20日下午12:06:29 
	 * @param uid
	 * @return
	 *
	 */
	public List<Comment> findByIdList(int uid);
	
}
