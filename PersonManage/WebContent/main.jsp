<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript">
			$(function(){
				var treeData;
				if($("#role").val()=="0"){
					 treeData=[{
						text:"外协人员管理系统",
						children:[{
							text:"部门管理",
							attributes:{
								url:"DepartmentManage.jsp"
							}
						},{
							text:"员工管理",
							attributes:{
								url:"EmployeeManage.jsp"
							}
						}]
					}];
				}else{
					 treeData=[{
						text:"外协人员管理系统",
						children:[{
							text:"员工管理",
							attributes:{
								url:"EmployeeManage.jsp"
							}
						}]
					}];
				}
				
				//实例化树型
				$("#tree").tree({
					data:treeData,
					lines:true,
					onClick:function(node){
						if(node.attributes){
							openTab(node.text,node.attributes.url);
						}
					}
				})
				//新增tab
				function openTab(text,url){
					if($("#tabs").tabs('exists',text)){
						$("#tabs").tabs('select',text);
					}else{
						var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%;' src="+url+"></iframe>";
						$("#tabs").tabs('add',{
							title:text,
							closable:true,
							content:content			
						});				
					}
				}
			});
		
				function logout() {
					location.href= "/PersonManage/loginout.action";
				}
				function openChangePasswordDialog(){
					$('#dlg').dialog('open').dialog("setTitle","修改密码");
					resetValue();
					url=("changePwd!changePwd");
				}
				function savePwd(){
					var newPwd=$.trim($("#newPassword").val());
					var conformPwd=$.trim($("#confirmPassword").val());
					if(newPwd!==conformPwd){
						alert("新密码与确认密码不一致");
						return ;
					}
					$('#changePassword').form("submit",{
						url:url,
						onSubmit:function(){
							return $(this).form("validate");
						},
						success:function(result){
							var result = eval("("+result+")");							
							if(result.errorMsg){
								$.messager.alert("系统提示",result.errorMsg);
								resetValue();
							}else{
								$.messager.alert("系统提示","保存成功");
								resetValue();
								$("#dlg").dialog("close");
								$("#dg").datagrid("reload");
							}
						}
					});
				}
				function closePwdDialog(){
					$('#dlg').dialog("close");
					resetValue();
				}
				function resetValue(){
					$('#oldPassword').val("");
					$('#newPassword').val("");
					$('#confirmPassword').val("");
				}
		</script>
<title>外协人员管理系统主界面</title>
<%
	//权限验证
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("index.jsp");
		return;
	}
%>
</head>
<body class="easyui-layout">
        <div data-options="region:'north'" style="height:100px;background-color:#F9F7F8">
        	<img alt="" src="images/main.jpg" style="height:98px;width:700px;float:left;"  >
        	<div style="margin:60px 50px 20px;float:right;">当前登录用户：&nbsp;<font color="red">${currentUser.userName }</font></div>
        	<div style="margin:60px 50px 20px;float:right;"><a href="javascript:logout();">退出登录</a></div>
        	<div style="margin:60px 50px 20px;float:right;"><a href="javascript:openChangePasswordDialog()">修改密码</a></div>
        </div>
        <input type="hidden" id="role" value="<%=session.getAttribute("role") %>"/>
        <!-- <div data-options="region:'south'" style="height:30px;padding:5px;" align="center"  >版权所有&nbsp;&nbsp;<a href="http://www.java1234.com">www.Java1234.com</a></div> -->
        <div data-options="region:'west',split:true" title="导航菜单" style="width:123px;">
        	<ul id="tree" class="easyui-tree" ></ul>
        </div>
        <div data-options="region:'center'">
            <div class="easyui-tabs" fit="true" border="false" id="tabs" >
				<div title="首页" style="padding:10px">
					<div align="center" style="padding-top:100px;"><font color="red" size="7">欢迎使用</font></div>
				</div>
			</div>
				
        </div>
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 250px; padding: 20px 10px 0;"
		closed="true" buttons="#dlg-buttons">
		<form id="changePassword" method="post">
			<table>
				<tr>
					<td>&nbsp;旧密码&nbsp;</td>
					<td><input type="password" name="oldPassword" id="oldPassword"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>新密码</td>
					<td><input type="password" name="newPassword" id="newPassword"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>确认密码</td>
					<td><input type="password" name="confirmPassword"
						id="confirmPassword" class="easyui-validatebox" required="true" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:savePwd()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
		<a href="javascript:closePwdDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
	</div>
</body>
</html>