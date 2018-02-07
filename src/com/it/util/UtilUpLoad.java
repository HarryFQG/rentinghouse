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
	//上传文件存储目录
	private static final String UPLOAD="upload";
	
	
	//上传配置
	private static final int MEMORY_THRESHOLD=1024*1024*3;//缓存3M
	private static final int MAX_FILE_SIZE=1024*1024*1500;//最大上传文件大小40M
	private static final int MAX_REQUEST_SIZE=1024*1024*1500;//请求最大尺寸50M
	private static Class clazz;
	
	
	public static Object upLoad(HttpServletRequest request,HttpServletResponse response,Class cla,String[] strArray) {
		clazz=cla;
		Object bean=null ;
		try{
			bean= clazz.newInstance();//获得实体类的实例
			request.setCharacterEncoding("utf-8");
			//检测是否为多媒体上传
		
				if(!ServletFileUpload.isMultipartContent(request)){
					//如果不是就停止上传
					PrintWriter writer=response.getWriter();
					writer.println("Error:表单必须包含 enctype=multipart/form-data");
					
					writer.close();//关闭时就刷新了
					return null;
				}
				
				//配置上传参数
				DiskFileItemFactory factory=new DiskFileItemFactory();
				//设置内存临界值----超过后将产生临时文件并存储 于临时目录中
				factory.setSizeThreshold(MEMORY_THRESHOLD);
				//设置临时存储目录
				factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
				
				
				ServletFileUpload upload=new ServletFileUpload(factory);
				
				//设置最大文件上传
				upload.setFileSizeMax(MAX_FILE_SIZE);
				//设置最大请求值
				upload.setSizeMax(MAX_REQUEST_SIZE);;
				
				//中文处理
				upload.setHeaderEncoding("utf-8");
				
				//构造临时路径来存储上传的文件，这个路径相对当前应用的目录
				String uploadPath=request.getServletContext().getRealPath("/")+UPLOAD;//目录的拼接
				
				//如果目录不存在则创建
				
				File uploadDir=new File(uploadPath);
				if(!uploadDir.exists()){
					uploadDir.mkdirs();//创建文件夹
					
				}
				
				
				
				
				//解析请求的内容提取文件数据
				List<FileItem> formItems=upload.parseRequest(request);
				if(formItems!=null&&formItems.size()>0){
					
					for(FileItem item:formItems){
						
						if(!item.isFormField()){
							//处理不在表单中的字段,也就是上传的路径
							String fileName=new File(item.getName()).getName();
							System.out.println("fileName:"+fileName);
							
							
							/*新文件命名：
							 * String houzhui=fileName.substring(fileName.lastIndexOf("."),fileName.length());
							 *path=sSystem.currentTimeMillis()+cuhouzhui;
							 * 
							 * */
							
							/*String[] str1=fileName.split("\\.");//切割文件完整名，后缀就是文件类型名
						
							String str=UUID.randomUUID().toString().replace("-","");//生成唯一标示文件名									
*/							
							String houzhui=fileName.substring(fileName.lastIndexOf("."),fileName.length());
							String	path1=System.currentTimeMillis()+houzhui;
							String filePath=uploadPath+File.separator+path1;							
							String path=UPLOAD+"/"+path1;
							
							System.out.println("path----:"+path+",date:"+path);
							for(int i=0;i<strArray.length;i++){
								if(item.getFieldName().equals(strArray[i])){
									//反射字段
									Field f=clazz.getDeclaredField(strArray[i]);
									f.setAccessible(true);
									f.set(bean, path);//保存路径									
									
									File storeFile=new File(filePath);
									//在控制台输出文件的上传路径
									System.out.println(filePath);						
									//保存文件到硬盘
									//item.write(storeFile);//还可以是这个	item.getInputStream()
									
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
									request.setAttribute("Msg", "文件上传成功！");
									
								}
							}
							
							
												
							
						}else{
							
							//两层循环嵌套一对一对照找
							for(int i=0;i<strArray.length;i++){
								
								if(item.getFieldName().equals(strArray[i])){
									//通过字段反射
									Field f = clazz.getDeclaredField(strArray[i]);
									f.setAccessible(true);//设置java的检查机制，
									String value=item.getString("utf-8");								
									
									String typestr = f.getType().toString();
									String type;
									if(typestr.contains(".")){
										type=typestr.substring(typestr.lastIndexOf(".")+1, typestr.length());
									}else{
										type=typestr;
									}		
											
									System.out.println("----type:"+type);
									//value = new String(value.getBytes("ISO-8859-1"),"utf-8");//这句话必须要，编码问题
									
									
									
									
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
			request.setAttribute("Msg", "文件上传失败！"+e.getMessage());
			throw new RuntimeException("房屋上传失败！");
		}
		
		
		return bean;
	}
	
	
	/**
	 * 解决：使用类似重载的方式匹配各种文件
	 * @param request
	 * @param response
	 */
	public static int downFile(HttpServletRequest request,HttpServletResponse response,String path){
		
		try {
			String format="";
			if(path!=null&&path.trim()!=""){
				format=path.substring(path.lastIndexOf(".")+1, path.length());
			}else throw new RuntimeException("请传入有效路径！");
			
			//1.设置输出的文件类型,可以从tomcat的web.xml里面查去获得文件格式			
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
			
			
			
			
			//2.设置输出的头部信息，固定写法.原始一句话
		
			String fileName=System.currentTimeMillis()+"."+format;
			String header="attachment;filename="+fileName;
			response.addHeader("Content-Disposition", header);//这种写法比下面的灵活些···
			/*response.addHeader("Content-Disposition", "attachment;filename=abc.pdf");
			*/
			path = new String(path.getBytes("ISO-8859-1"),"utf-8");
			path=request.getServletContext().getRealPath("/")+path;;
			//3.指定需要提供下载的文件
			File file=new File(path);
			FileInputStream fis=new FileInputStream(file);//输入流
					
					
			//4.设置响应的正文大小
			response.setContentLength((int)file.length());
			ServletOutputStream os = response.getOutputStream();//不用指定下载的路径，有浏览器自己指定
					
			
			//文件的复制
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
	 * 下午4:03:30
	 * @param request 
	 * @param path 相对路径
	 * @return 操作结果
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
