package com.it.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.it.entity.Comment;
import com.it.util.DbUtilPool;

public class CommentDaoImpl implements CommentDao{

	QueryRunner query=null;
	public CommentDaoImpl(){
		
		try {
				query=new QueryRunner(DbUtilPool.getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建CommentDaoImpl失败。");
		}		
		
	}
	
	@Override
	public int add(Comment t) {
		String sql="insert into tb_comment(comment_replay,comment_content,comment_createtime,comment_replaytime,comment_cid,comment_rid,comment_cname,comment_rname,commet_status)values(?,?,?,?,?,?,?,?,?);";
		try {
		return 	query.update(sql, t.getCreplay(),t.getCcontent(),new java.sql.Timestamp(t.getCcreateTime().getTime()),new java.sql.Timestamp(t.getCreplayTmie().getTime()),t.getCcid(),t.getCrid(),t.getCcname(),t.getCrname(),t.getCstatus());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加留言失敗！");
		}
		
		
	}

	@Override
	public int delete(Serializable s) {
		
		return 0;
	}

	@Override
	public int update(Comment t) {
		
		return 0;
	}

	@Override
	public List<Comment> list() {
		
		return null;
	}

	@Override
	public List<Comment> listByPage(int currentPage, int pageSize) {
		
		return null;
	}

	@Override
	public Comment findById(int id) {
		
		return null;
	}

	@Override
	public List<Comment> findById2(Integer fangDongId, Integer userId) {// or ( comment_rid=? and comment_cid=?)
		String sql="select comment_id as cid ,comment_replay as creplay ,comment_content as ccontent,comment_createtime as ccreateTime,comment_replaytime as creplayTmie,comment_cid as ccid,comment_rid as crid ,comment_cname as ccname ,comment_rname as crname,commet_status as cstatus from tb_comment where (comment_cid in(?,?)) and (comment_rid in(?,?)) order by comment_id asc; ";
		try {
			return 	query.query(sql, new BeanListHandler<Comment>(Comment.class), userId,fangDongId,fangDongId,userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("findById2更据userId和fangDongId查询失败！");
		}	
	}
	
	@Override
	public int getCount() {
		
		return 0;
	}

	@Override
	public int changStatus(int status) {
		
		return 0;
	}

	@Override
	public List<Comment> findByIdList(int uid) {
		String sql="select distinct comment_cid as ccid,comment_cname as ccname , comment_rid as crid,comment_rname as crname  from tb_comment where comment_cid=? or comment_rid=?;";
		try {
			return	query.query(sql, new BeanListHandler<Comment>(Comment.class), uid,uid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询回复列表异常");
		}
		
	}

	

}
