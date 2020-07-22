/**
 * 用户列表删除点击事件！
 */
function delUser(id,name) {
	mizhu.confirm('温馨提醒', '要删除该用户吗？','确认', '取消', function(flag) {
		if (flag) {
			
			// ajax实现删除！
			$.ajax({
				url : contextPath + "/pre/del",
				method : "post",
				data : {id:id,name:name},
				dataType : "json",
				success : success,
				error : error,
			});
			// 删除成功回调函数
			function success(data) {
				var da =eval(data);
				if (da.status == 1) {
					mizhu.toast(da.message, 2000);
					var currentPage = $("#thisCurrentPage").val();
					var at = {"currentPage" : currentPage};
					$(".m_right").load(contextPath + "/pre/userList .m_right>*",at);
				}else{
					mizhu.alert('操作提醒',da.message);
				}
			}
			//请求失败回调函数
			function error(data) {
				alert("请求失败!");
			}
			/*$.post("EasybuyUserServlet", "action=del&id=" + id, function(del) {
				// 删除成功与否弹出信息！
				var result = eval("(" + del + ")");
				
				if (result.status == 1) {
					mizhu.toast(result.message, 2000);
					var currentPage = $("#thisCurrentPage").val();
					var ise = {
						"currentPage" : currentPage
					};
					$(".m_right").load(
							contextPath
									+ "/EasybuyUserServlet?action=user .m_right>*",
							ise);
				}else{
					mizhu.alert('操作提醒',result.message);
				}
				
			});*/
		}
	})

}
/**
 * 点击修改
 * @param id
 * @returns
 */
function updateByUserId(id,currentPage) {
	//ajax实现修改
	/*$.ajax({
		url : "EasybuyUserServlet",
		method : "post",
		data : "action=toUpdateUser&id=" + id+"&currentPage="+currentPage,
		dataType : "json",
		success : success,
		error : error,
	});
	// 修改成功回调函数
	function success(data) {
		var da =eval(data);
		if (da.status == 1) {
			
			$(".m_right").load("EasybuyUserServlet?action=user .m_right>*",da);
		}else{
			mizhu.alert('操作提醒',da.message);
		}
	}
	//请求失败回调函数
	function error(data) {
		alert("请求失败!");
	}*/
	
	alert(id);
	var ise = {"id" : id,"currentPage":currentPage};
	$(".m_right").load(contextPath + "/pre/toUpdateUser .m_right>*",ise);
}
/**
 * 点击添加
 * @returns
 */
function addUser() {
	
	$(".m_right").load(contextPath + "/pre/add .m_right>*");
}