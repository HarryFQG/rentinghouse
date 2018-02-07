package com.it.dao;

import java.util.List;

import com.it.entity.Comment;
/**
 * 
 * @author Administrator 2017��8��14�� ����2:01:56
 *
 */
public interface CommentDao extends IDao<Comment>{
	/**
	 * ����2:04:48
	 * վ����Ϣ�Ķ�����״̬�ĸı�
	 * @param status
	 * @return
	 *
	 */
	public int changStatus(int status);

	/**
	 * 2017��8��18������6:54:31 
	 * @param fangDongId
	 * @param userId
	 *	��ѯ�Ự
	 * @return 
	 */
	public List<Comment> findById2(Integer fangDongId, Integer userId);
	
	
	/**��ѯ�ػ��б�
	 * 2017��8��20������12:06:29 
	 * @param uid
	 * @return
	 *
	 */
	public List<Comment> findByIdList(int uid);
	
}
