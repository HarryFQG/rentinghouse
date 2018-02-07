package com.it.util;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class SolrUtil {
	     
	 private static SolrUtil  solrServer=null;
	 //  SolrServer是当前类（单例模式），HttpSolrServer是 http服务器
	 private static HttpSolrServer server=null;
	 private static String url="http://localhost:8042/solr/house" ;
	     
	     /*为了线程安全，这个方法加锁。相当于不管哪一个线程，运行到这个方法时，
	     	都要检查有没有其它线程B在用。有,等待；无，直接运行	  */ 
	 public static synchronized SolrUtil getInstance(){
	     if(solrServer ==null){
	             solrServer=new SolrUtil();
	     }
	         return solrServer ;
	     }
	     
	     public static HttpSolrServer getServer(){
	           if(server ==null){
	               server=new HttpSolrServer(url);
	               //设置对应请求的目标主机线程数为1000条
	              server.setDefaultMaxConnectionsPerHost(1000);
	              server.setConnectionTimeout(10000);//设置连接超时时间（单位毫秒）
	              server.setSoTimeout(1000);//设置读数据超时时间(单位毫秒)
	              server.setFollowRedirects(false);//遵循从定向
	              server.setAllowCompression(true);//允许压缩
	              server.setMaxRetries(1);//重试
	              
          }
	           return server ;          
	     }   
	     
	     /*public static void main(String[] args) {
	    	HttpSolrServer server= getServer();
	    	SolrQuery query=new SolrQuery();
	    	query.setQuery("*:*");
	    	try {
				UpdateResponse deleteByQuery = server.deleteByQuery("*:*");
				System.err.println("----"+deleteByQuery);
			} catch (SolrServerException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
}
	
	
	
	

