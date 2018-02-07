/**
 * �Ե��ú���  
 */
(function(){
	
	var getElement=function(id) {return document.getElementById(id);};//ͨ��DOM�����idΪId�� Ԫ��
	window.getElement=getElement;
	
	//��װ Ajax��ʼ,��ʼ��Ajax���ң������ʺϵ�ǰ���������XMLHttpRequest����
	getElement.init=function(){
		
		try {
			return new XMLHttpRequest();
		} catch (e) {}
		try {
			return new ActiveXObject("Microsoft.XMLHTTP");//IE��������е�
		} catch (e) {}
		alter("Old Broswer!");
		
	};
	//��װAjax��Get��ʽ������
	getElement.Get=function(url,data,callback,type){
		/**
		 * ����˵����
		 * 		url:����ĵ�ַ
		 * 		data:���󴫹�ȥ������
		 * 		callback���ص�������������onreadystatechange��ʱ����ã�ע����Ӧ��
		 * 		type:�Է������˷��صĸ�ʽ˵����XML,Json,Text��
		 */
		if(data!=null){
			url=url+"?"+data;
		}
		var xhr=getElement.init();//���Ajax����
		xhr.open("Get",url,true);
		xhr.setRequestHeader("If-Modified-Since","0");//����ͷ����ֹ�Ի��棬ȷ��ÿһ�ζ���������������󡣵��ǲ�û����û��������⡣
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				if(type==null){
					type="Text";
				}
				if(type=="XML"){
					callback(xhr.responseXML);
				}
				if(type=="JSON"){
					var json=eval("("+xhr.responseText+")");//�������ַ���һ��Ҫ��eval����ת��ΪJSON�������������飬�Ͳ���Ҫ
					callback(json);
				}
				/*if(type=="JSON1"){
					var json=xhr.respponseText;
					callback(json);
				}*/
				
			}
			
		};
		xhr.send(null);
	};
	
	//��װpost����
	getElement.Post=function(url,data,callback,type){
		/**
		 * ����˵����
		 * 		url:����ĵ�ַ
		 * 		data:���󴫹�ȥ������
		 * 		callback���ص�������������onreadystatechange��ʱ����ã�ע����Ӧ��
		 * 		type:�Է������˷��صĸ�ʽ˵����XML,Json,Text��
		 */
		var xhr=getElement.init();
		//alert("Url=  "+url+"  ,Data=   "+data+" ,Type="+type);
		xhr.open("post",url,true);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		xhr.onreadystatechange=function(){	
			//alert("public.js.Post.onreadystatechange.if()-->>"+"readyState:"+xhr.readyState+"xhr.status:"+xhr.status);
			if(xhr.readyState==4&&xhr.status==200){
				//alert("public.js.Post.onreadystatechange.if()-->>");
				if(type==null){
					type="Text";
				}
				if(type=="XML"){
					callback(xhr.responseXML);
				}
				if(type=="JSON1"){
					alert("public.js.Post.JSON1:");
					var json=eval("("+xhr.responseText+")");//�������ַ���һ��Ҫ��eval����ת��ΪJSON�������������飬�Ͳ���Ҫ
					alert("public.js.Post.JSON1:"+json);
					callback(json);//json����
				}
				if(type=="JSON2"){
					//alert("public.js.Post.JSON:");
					var json=eval("("+xhr.responseText+")");//�������ַ���һ��Ҫ��eval����ת��ΪJSON�������������飬�Ͳ���Ҫ
					
					//alert("public.js.Post.JSON:"+json);
					callback(json);//json����
				}
			}
			
		};
		//alert("data:--->>"+data);
		xhr.send(data);
	};
	
	
})();