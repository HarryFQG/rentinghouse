package com.it.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 
 * @author Administrator 2017��8��15�� ����12:53:06
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
			pageList.append("����"+totalRow+"����¼");
			pageList.append("<a href=\""+redirectUrl+"&"+param+"=1\">��ҳ</a>");
			pageList.append("<a href=\""+redirectUrl+"&"+param+"="+(currentPage-1)+"\">��һҳ</a>");
			pageList.append("<a href=\""+redirectUrl+"&"+param+"="+(currentPage+1)+"\">��һҳ</a>");
			pageList.append("<a href=\""+redirectUrl+"&"+param+"="+(totalPage)+"\">βҳ</a>");
			pageList.append("<input type=\"text\" id=\"zhiding\" value=\"\" style=\"width:30px\"/><a onclick=\"javascript:func1()\" >Go</a>");
			pageList.append(" ��ǰҳ<input type=\"text\" value=\""+currentPage+"\" style=\"width: 30px\" readonly=\"readonly\"/>");
			pageList.append("��ҳ��<input type=\"text\" value=\""+totalPage+"\" style=\"width: 30px\" readonly=\"readonly\"/>");
		/*}*/
		pageList.append("</div>");
		out.write(pageList.toString());
	}


	
}
