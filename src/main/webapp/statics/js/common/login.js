/**
 * 窗体加载事件！
 * 
 * @returns
 */
$(document).ready(function() {
	/**
	 * 登陆按钮点击事件！
	 */
	$("#log_btn").click(function() {
		loginCheck();
	});

	/**
	 * 回车键点击事件！
	 */
	$('form').on('keypress', function(event) {
		if (event.keyCode == 13) {
			loginCheck();
		}
	});

	/**
	 * 登录验证！
	 */
	function loginCheck() {
		var name = $(".l_user").val();
		var pwd = $(".l_pwd").val();
		if (name == "" || name == null) {
			mizhu.alert('操作提醒', "用户名不能为空!");
			return;
		} else if (pwd == "" || pwd == null) {
			mizhu.alert('操作提醒', "密码不能为空!");
			return;
		} else {
			//登录ajax请求
			$.ajax({
				url : contextPath+"/login",
				method : "post",
				data : {
					"name" : name,
					"pwd" : pwd
				},
				dataType : "json",
				success : function(data) {
					if(data.loginResult == "true"){
						// 登录成功！跳转登录界面！！
						location.href = contextPath + "/index";
					
					}else {
						mizhu.alert('操作提醒',data.message);
					}
				}
			});
			
			
			
			
          
		}
	}
	
});

