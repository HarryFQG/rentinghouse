package com.it.util;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class SolrUtil {
	     
	 private static SolrUtil  solrServer=null;
	 //  SolrServer�ǵ�ǰ�ࣨ����ģʽ����HttpSolrServer�� http������
	 private static HttpSolrServer server=null;
	 private static String url="http://localhost:8042/solr/house" ;
	     
	     /*Ϊ���̰߳�ȫ����������������൱�ڲ�����һ���̣߳����е��������ʱ��
	     	��Ҫ�����û�������߳�B���á���,�ȴ����ޣ�ֱ������	  */ 
	 public static synchronized SolrUtil getInstance(){
	     if(solrServer ==null){
	             solrServer=new SolrUtil();
	     }
	         return solrServer ;
	     }
	     
	     public static HttpSolrServer getServer(){
	           if(server ==null){
	               server=new HttpSolrServer(url);
	               //���ö�Ӧ�����Ŀ�������߳���Ϊ1000��
	              server.setDefaultMaxConnectionsPerHost(1000);
	              server.setConnectionTimeout(10000);//�������ӳ�ʱʱ�䣨��λ���룩
	              server.setSoTimeout(1000);//���ö����ݳ�ʱʱ��(��λ����)
	              server.setFollowRedirects(false);//��ѭ�Ӷ���
	              server.setAllowCompression(true);//����ѹ��
	              server.setMaxRetries(1);//����
	              
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
	
	
	
	

