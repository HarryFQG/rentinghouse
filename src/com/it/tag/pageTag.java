package com.it.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 
 * @author Administrator 
 *
 */
public class pageTag extends SimpleTagSupport{

	private Integer currentPage;
	private Integer totalPage;
	private Integer totalRow;
	private String redirectUrl;
	private String param;
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out=getJspContext().getOut();
		StringBuffer pageList=new StringBuffer();
		/*totalPage=(totalPage==null||totalPage<1)?0:totalPage;
		if(totalRow%pageSize==0){
			totalPage=totalRow/pageSize;
		}else if(totalRow%pageSize!=0){
			totalPage=totalRow/pageSize+1;
		}
		if(currentPage==null||currentPage<=1){
			currentPage=1;
		}else if(currentPage>=totalPage){
			currentPage=totalPage;
		}*/
		pageList.append("<div align='left'>");
		/*if(totalPage>1){*/
			pageList.append("共有"+totalRow+"条记录");
			pageList.append("<a href=\""+redirectUrl+"&"+param+"=1\">首页</a>");
			pageList.append("<a href=\""+redirectUrl+"&"+param+"="+(currentPage-1)+"\">上一页</a>");
			pageList.append("<a href=\""+redirectUrl+"&"+param+"="+(currentPage+1)+"\">下一页</a>");
			pageList.append("<a href=\""+redirectUrl+"&"+param+"="+(totalPage)+"\">尾页</a>");
			pageList.append("<input type=\"text\" id=\"zhiding\" value=\"\" style=\"width:30px\"/><a onclick=\"javascript:func1()\" >Go</a>");
			pageList.append(" 当前页<input type=\"text\" value=\""+currentPage+"\" style=\"width: 30px\" readonly=\"readonly\"/>");
			pageList.append("总页数<input type=\"text\" value=\""+totalPage+"\" style=\"width: 30px\" readonly=\"readonly\"/>");
		/*}*/
		pageList.append("</div>");
		out.write(pageList.toString());
	}


	
}
