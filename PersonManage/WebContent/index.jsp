<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>外协人员管理系统登录</title>
<script type="text/javascript">
	function resetValue(){
		document.getElementById("userName").value="";
		document.getElementById("password").value="";
	}
</script>
<style type="text/css">
	body{
		font-size: 12px;
		margin:0;
		padding:0;
		width:100%
	}
	#main{
		background: url("images/login.jpg") no-repeat;
		background-position: center;
		/* background-attachment: fixed; */
		width:1200px;
		height:650px;	
		/* margin:0 auto; */
		/* position:relative; */
		position:absolute;
	}
	form{
	    /* margin:0 auto;  */
		position:absolute;
		left:380px;
		top:425px;
	}
	#input{
	}
	.inp{
		width: 100px;
	}
	#error{
		padding:10px 0;
	}
</style>
</head>
<body>

	<div id="main">
	<img src="images/logo.jpg"  />
		<form action="login" method="post" >
			<table id="input">
				<tr>
					<td>用户名：</td>
					<td><input type="text" value="" class="inp" name="user.userName" id="userName"/></td>
					<td>密&nbsp;码：</td>
					<td class="name"><input type="password" value="" class="inp" name="user.password" id="password" /></td>
					<td><input type="submit" value="登录"/></td>
					<td><input type="button" value="重置" onclick="resetValue()" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td id="error"><font color="red">${error}</font></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>