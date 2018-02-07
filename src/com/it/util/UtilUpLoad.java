package com.it.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UtilUpLoad {
	private static final long serialVersionUID=1L;
	//�ϴ��ļ��洢Ŀ¼
	private static final String UPLOAD="upload";
	
	
	//�ϴ�����
	private static final int MEMORY_THRESHOLD=1024*1024*3;//����3M
	private static final int MAX_FILE_SIZE=1024*1024*1500;//����ϴ��ļ���С40M
	private static final int MAX_REQUEST_SIZE=1024*1024*1500;//�������ߴ�50M
	private static Class clazz;
	
	
	public static Object upLoad(HttpServletRequest request,HttpServletResponse response,Class cla,String[] strArray) {
		clazz=cla;
		Object bean=null ;
		try{
			bean= clazz.newInstance();//���ʵ�����ʵ��
			request.setCharacterEncoding("utf-8");
			//����Ƿ�Ϊ��ý���ϴ�
		
				if(!ServletFileUpload.isMultipartContent(request)){
					//������Ǿ�ֹͣ�ϴ�
					PrintWriter writer=response.getWriter();
					writer.println("Error:��������� enctype=multipart/form-data");
					
					writer.close();//�ر�ʱ��ˢ����
					return null;
				}
				
				//�����ϴ�����
				DiskFileItemFactory factory=new DiskFileItemFactory();
				//�����ڴ��ٽ�ֵ----�����󽫲�����ʱ�ļ����洢 ����ʱĿ¼��
				factory.setSizeThreshold(MEMORY_THRESHOLD);
				//������ʱ�洢Ŀ¼
				factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
				
				
				ServletFileUpload upload=new ServletFileUpload(factory);
				
				//��������ļ��ϴ�
				upload.setFileSizeMax(MAX_FILE_SIZE);
				//�����������ֵ
				upload.setSizeMax(MAX_REQUEST_SIZE);;
				
				//���Ĵ���
				upload.setHeaderEncoding("utf-8");
				
				//������ʱ·�����洢�ϴ����ļ������·����Ե�ǰӦ�õ�Ŀ¼
				String uploadPath=request.getServletContext().getRealPath("/")+UPLOAD;//Ŀ¼��ƴ��
				
				//���Ŀ¼�������򴴽�
				
				File uploadDir=new File(uploadPath);
				if(!uploadDir.exists()){
					uploadDir.mkdirs();//�����ļ���
					
				}
				
				
				
				
				//���������������ȡ�ļ�����
				List<FileItem> formItems=upload.parseRequest(request);
				if(formItems!=null&&formItems.size()>0){
					
					for(FileItem item:formItems){
						
						if(!item.isFormField()){
							//�����ڱ��е��ֶ�,Ҳ�����ϴ���·��
							String fileName=new File(item.getName()).getName();
							System.out.println("fileName:"+fileName);
							
							
							/*���ļ�������
							 * String houzhui=fileName.substring(fileName.lastIndexOf("."),fileName.length());
							 *path=sSystem.currentTimeMillis()+cuhouzhui;
							 * 
							 * */
							
							/*String[] str1=fileName.split("\\.");//�и��ļ�����������׺�����ļ�������
						
							String str=UUID.randomUUID().toString().replace("-","");//����Ψһ��ʾ�ļ���									
*/							
							String houzhui=fileName.substring(fileName.lastIndexOf("."),fileName.length());
							String	path1=System.currentTimeMillis()+houzhui;
							String filePath=uploadPath+File.separator+path1;							
							String path=UPLOAD+"/"+path1;
							
							System.out.println("path----:"+path+",date:"+path);
							for(int i=0;i<strArray.length;i++){
								if(item.getFieldName().equals(strArray[i])){
									//�����ֶ�
									Field f=clazz.getDeclaredField(strArray[i]);
									f.setAccessible(true);
									f.set(bean, path);//����·��									
									
									File storeFile=new File(filePath);
									//�ڿ���̨����ļ����ϴ�·��
									System.out.println(filePath);						
									//�����ļ���Ӳ��
									//item.write(storeFile);//�����������	item.getInputStream()
									
									InputStream is = item.getInputStream();
									BufferedInputStream bis=new BufferedInputStream(is);
									OutputStream os=new FileOutputStream(storeFile);
									BufferedOutputStream bos=new BufferedOutputStream(os);
									int len=0;
									byte[] b=new byte[1024*10];
									while((len=bis.read(b))!=-1){
										bos.write(b, 0, len);
									}
									bos.close();
									bis.close();
									request.setAttribute("Msg", "�ļ��ϴ��ɹ���");
									
								}
							}
							
							
												
							
						}else{
							
							//����ѭ��Ƕ��һ��һ������
							for(int i=0;i<strArray.length;i++){
								
								if(item.getFieldName().equals(strArray[i])){
									//ͨ���ֶη���
									Field f = clazz.getDeclaredField(strArray[i]);
									f.setAccessible(true);//����java�ļ����ƣ�
									String value=item.getString("utf-8");								
									
									String typestr = f.getType().toString();
									String type;
									if(typestr.contains(".")){
										type=typestr.substring(typestr.lastIndexOf(".")+1, typestr.length());
									}else{
										type=typestr;
									}		
											
									System.out.println("----type:"+type);
									//value = new String(value.getBytes("ISO-8859-1"),"utf-8");//��仰����Ҫ����������
									
									
									
									
									Object value1=null;
									if(value!=null&&value.trim()!=""){
										if(type.equals("int")||type.equals("Integer"))value1=Integer.parseInt(value);
										if(type.equals("float"))value1=Float.parseFloat(value);
										if(type.equals("double"))value1=Double.parseDouble(value);
										if(type.equals("boolean"))value1=Boolean.parseBoolean(value);
										if(type.equals("String"))value1=String.valueOf(value);
										if(type.equals("char"))value1=value.toCharArray()[0];
									}
										
										if(type.equals("Date")){
											String[] dateArr={"yy-MM-dd","yy-MM-dd HH-mm-ss","yy/MM/dd","yy/MM/dd HH/mm/ss","HH:mm:ss"};
											for(int y=0;y<dateArr.length;y++){
												try{
													SimpleDateFormat format=new SimpleDateFormat(dateArr[y]);
													Date date = format.parse(value);
													value1=date;
												}catch(Exception e){
													e.printStackTrace();
												}
												
											}										
										}										
									
									f.set(bean, value1);									
									
									System.out.println("value:"+value1);
								}
							}
							
						}
							
							
							
							
						}
						
					}
					
				
				
//			request.getRequestDispatcher("msg.jsp").forward(request, response);
				
		}catch(Exception e){
		//	e.getStackTrace();
			e.printStackTrace();
			request.setAttribute("Msg", "�ļ��ϴ�ʧ�ܣ�"+e.getMessage());
			throw new RuntimeException("�����ϴ�ʧ�ܣ�");
		}
		
		
		return bean;
	}
	
	
	/**
	 * �����ʹ���������صķ�ʽƥ������ļ�
	 * @param request
	 * @param response
	 */
	public static int downFile(HttpServletRequest request,HttpServletResponse response,String path){
		
		try {
			String format="";
			if(path!=null&&path.trim()!=""){
				format=path.substring(path.lastIndexOf(".")+1, path.length());
			}else throw new RuntimeException("�봫����Ч·����");
			
			//1.����������ļ�����,���Դ�tomcat��web.xml�����ȥ����ļ���ʽ			
			Map<String,String> map=new HashMap<String,String>();
			map.put("svg","image/svg+xml");map.put("tiff","image/tiff");
			map.put("gif","image/gif");map.put("jpeg","image/jpeg");
			map.put("flash","image/png");map.put("png","image/png");
			map.put("rmvb","application/vnd.rn-realmedia-vbr");map.put("wma","audio/x-ms-wma");
			map.put("txt","text/plain");map.put("bmp","image/bmp");
			map.put("mp4","audio/mp4");map.put("avi","video/x-msvideo");
			Set<String> set = map.keySet();			
			for(String key:set){
				if(key.equalsIgnoreCase(format)){
					response.setContentType(map.get(key));
				}
			}
			
			
			
			
			//2.���������ͷ����Ϣ���̶�д��.ԭʼһ�仰
		
			String fileName=System.currentTimeMillis()+"."+format;
			String header="attachment;filename="+fileName;
			response.addHeader("Content-Disposition", header);//����д������������Щ������
			/*response.addHeader("Content-Disposition", "attachment;filename=abc.pdf");
			*/
			path = new String(path.getBytes("ISO-8859-1"),"utf-8");
			path=request.getServletContext().getRealPath("/")+path;;
			//3.ָ����Ҫ�ṩ���ص��ļ�
			File file=new File(path);
			FileInputStream fis=new FileInputStream(file);//������
					
					
			//4.������Ӧ�����Ĵ�С
			response.setContentLength((int)file.length());
			ServletOutputStream os = response.getOutputStream();//����ָ�����ص�·������������Լ�ָ��
					
			
			//�ļ��ĸ���
			int len=0;
			byte[] b=new byte[1024];
			while((len=fis.read(b))!=-1){
				os.write(b, 0, len);			
			}
			fis.close();
			os.close();
		} catch (Exception e) {
			return 0;
		}
		return 1;
		
	}
	/**
	 * 
	 * ����4:03:30
	 * @param request 
	 * @param path ���·��
	 * @return �������
	 */
	public static int deleteFile(HttpServletRequest request,String path){
		String path1=request.getServletContext().getRealPath("/")+path;
		
		File file=new File(path1);
		boolean flag=file.delete();
		if(flag){
			return 1;
		}else{			
			return 0;
		}
		
	}
	
	
	
	
	
}
