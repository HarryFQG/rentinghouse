/**
 * 自调用函数  
 */
(function(){
	
	var getElement=function(id) {return document.getElementById(id);};//通过DOM来获得id为Id的 元素
	window.getElement=getElement;
	
	//封装 Ajax开始,初始化Ajax并且，创建适合当前的浏览器的XMLHttpRequest对象
	getElement.init=function(){
		
		try {
			return new XMLHttpRequest();
		} catch (e) {}
		try {
			return new ActiveXObject("Microsoft.XMLHTTP");//IE浏览器特有的
		} catch (e) {}
		alter("Old Broswer!");
		
	};
	//封装Ajax的Get形式的请求
	getElement.Get=function(url,data,callback,type){
		/**
		 * 参数说明：
		 * 		url:请求的地址
		 * 		data:请求传过去的数据
		 * 		callback：回调函数，就是在onreadystatechange的时候调用，注意是应用
		 * 		type:对服务器端返回的格式说明（XML,Json,Text）
		 */
		if(data!=null){
			url=url+"?"+data;
		}
		var xhr=getElement.init();//获得Ajax对象
		xhr.open("Get",url,true);
		xhr.setRequestHeader("If-Modified-Since","0");//请求头，防止对缓存，确保每一次都是向服务器端请求。但是并没解决用户缓存问题。
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				if(type==null){
					type="Text";
				}
				if(type=="XML"){
					callback(xhr.responseXML);
				}
				if(type=="JSON"){
					var json=eval("("+xhr.responseText+")");//这种是字符串一次要用eval函数转换为JSON对象，倘若是数组，就不需要
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
	
	//封装post请求
	getElement.Post=function(url,data,callback,type){
		/**
		 * 参数说明：
		 * 		url:请求的地址
		 * 		data:请求传过去的数据
		 * 		callback：回调函数，就是在onreadystatechange的时候调用，注意是应用
		 * 		type:对服务器端返回的格式说明（XML,Json,Text）
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
					var json=eval("("+xhr.responseText+")");//这种是字符串一次要用eval函数转换为JSON对象，倘若是数组，就不需要
					alert("public.js.Post.JSON1:"+json);
					callback(json);//json对象
				}
				if(type=="JSON2"){
					//alert("public.js.Post.JSON:");
					var json=eval("("+xhr.responseText+")");//这种是字符串一次要用eval函数转换为JSON对象，倘若是数组，就不需要
					
					//alert("public.js.Post.JSON:"+json);
					callback(json);//json对象
				}
			}
			
		};
		//alert("data:--->>"+data);
		xhr.send(data);
	};
	
	
})();