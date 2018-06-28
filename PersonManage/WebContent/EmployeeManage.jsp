<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.java1234.model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>
 		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
		<style type="text/css">
			input{
				width:80px;
			}
		</style>
		<script type="text/javascript">
			var url;
			function searchEmployee(){
				$('#dg').datagrid('load',{
					s_employeeNo:$('#s_employeeNo').val(),
					s_name:$('#s_name').val(),
					s_sex:$('#s_sex').combobox("getValue"),
					s_bbirthday:$('#s_bbirthday').datebox("getValue"),
					s_ebirthday:$('#s_ebirthday').datebox("getValue"),
					s_nationality:$('#s_nationality').val(),
					s_education:$('#s_education').val(),
					s_departmentId:$('#s_departmentId').combobox("getValue"),
					s_productLine:$('#s_productLine').val(),
					s_supplier:$('#s_supplier').val(),
					s_currentState:$('#s_currentState').combobox("getValue"),
					s_hiredate:$('#s_hiredate').datebox("getValue"),
					s_leaveDate:$('#s_leaveDate').datebox("getValue"),
					s_technicalDirection:$('#s_technicalDirection').val(),
					s_technicalLevel:$('#s_technicalLevel').val(),
					s_payDepartment:$('#s_payDepartment').val(),
					s_position:$('#s_position').val()					
				});				
			}
			function deleteEmployee(){
				var selectedRows=$("#dg").datagrid('getSelections');
				if(selectedRows.length==0){
					$.messager.alert("系统提示","请选择要删除的数据！");
					return;
				}
				var strIds=[];
				for(var i=0;i<selectedRows.length;i++){
					strIds.push(selectedRows[i].employeeId);
				}
				var ids=strIds.join(",");
				$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
					if(r){
						$.post("employee!delete",{delIds:ids},function(result){
							if(result.success){
								$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNum+"</font>条数据!");
								$("#dg").datagrid("reload");
							}else{
								$.messager.alert("系统提示",'<font color=red>'+selectedRows[result.errorIndex].name+'</font>'+result.errorMsg);
							}
						},"json");
					}
				});
			}
			function openEmployeeAddDialog(){
				$('#dlg').dialog('open').dialog("setTitle","员工信息");
				resetValue();
				url=("employee!save");
			}
			
			function openEmployeeModifyDialog(){
				var selectedRows=$("#dg").datagrid('getSelections');
				if(selectedRows.length!=1){
					$.messager.alert("系统提示","请选择一条要编辑的数据！");
					return;
				}
				var row=selectedRows[0];
				$("#dlg").dialog("open").dialog("setTitle","编辑员工信息");
				$("#employeeNo").val(row.employeeNo);
				$("#name").val(row.name);
				$("#sex").combobox("setValue",row.sex);
				$('#birthday').datebox("setValue",row.birthday);
				$("#departmentId").combobox("setValue",row.departmentId);
				$("#nationality").val(row.nationality);
				$("#education").val(row.education);
				$("#profession").val(row.profession);
				$("#position").val(row.position);
				$("#baseMoney").val(row.baseMoney);
				$("#overtime").val(row.overtime);
				$("#age").val(row.age);
				$("#check1").val(row.check1);
				$("#absent").val(row.absent);
				$("#safety").val(row.safety);
				$("#currentState").combobox("setValue",row.currentState);
				$("#hiredate").datebox("setValue",row.hiredate);
				$("#leaveDate").datebox("setValue",row.leaveDate);
				$("#idcardno").val(row.idcardno);
				$("#phone").val(row.phone);
				$("#productLine").val(row.productLine);
				$("#supplier").val(row.supplier);
				$("#technicalDirection").val(row.technicalDirection);
				$("#technicalLevel").val(row.technicalLevel);
				$("#payDepartment").val(row.payDepartment);
				url="employee!save?employeeId="+row.employeeId;
			}
			
			function closeEmployeeDialog(){
				$('#dlg').dialog("close");
				resetValue();
			}
			
			function resetValue(){
				$('#employeeNo').val("");
				$('#name').val("");
				$('#sex').combobox("setValue","");
				$('#bbirthday').datebox("setValue","");
				$('#departmentId').combobox("setValue","");
				$('#nationality').val("");
				$('#education').val("");
				$('#profession').val("");
				$('#position').val("");
				$("#baseMoney").val("");
				$("#overtime").val("");
				$("#age").val("");
				$("#check1").val("");
				$("#absent").val("");
				$("#safety").val("");
				$("#productLine").val("");
				$("#supplier").val("");
				$("#currentState").combobox("setValue","");
				$("#hiredate").datebox("setValue","");
				$("#leaveDate").datebox("setValue","");
				$("#idcardno").val("");
				$("#phone").val("");
				$("#technicalDirection").val("");
				$("#technicalLevel").val("");
				$("#payDepartment").val("");
			}
			
			function saveEmployee(){
				$('#fm').form("submit",{
					url:url,
					onSubmit:function(){
						return $(this).form("validate");
					},
					success:function(result){
						var result = eval("("+result+")");
						if(result.errorMsg){
							$.messager.alert("系统提示",result.errorMsg);
							return;
						}else{
							$.messager.alert("系统提示","保存成功");
							resetValue();
							$("#dlg").dialog("close");
							$("#dg").datagrid("reload");
						}
					}
				});
			}
			function exportEmployee(){
				$('#search').form("submit",{
					url:"employee!ExportEmployee"
				})
				//window.location.href="employee!ExportEmployee";
			}
		</script>
</head>
<body>
	<table id="dg" title="" class="easyui-datagrid" style="width:700px;height:250px"
				fitColumns="true" rownumbers="true" fit="true" pagination="true" url="employee" toolbar="#tb" >
			<thead>
				<tr>
					<th field="cb" checkbox="true" ></th>
					<th data-options="field:'employeeId'" width="5" hidden="true">ID</th>
					<th data-options="field:'employeeNo'" width="10">编号</th>
					<th data-options="field:'name'" width="10">姓名</th>
					<th data-options="field:'sex'" width="10">性别</th>
					<th data-options="field:'birthday'" width="12">出生日期</th>
					<th data-options="field:'nationality'" hidden="true" width="10">民族</th>
					<th data-options="field:'education'" width="10">学历</th>
					<th data-options="field:'profession'" width="10">专业</th>
					<th data-options="field:'departmentName'" width="10">所在部门</th>
					<th data-options="field:'productLine'" width="10">所在项目组</th>
					<th data-options="field:'idcardno'" width="18">身份证</th>
					<th data-options="field:'phone'" width="10">联系电话</th>
					<th data-options="field:'hiredate'" width="12">入职时间</th>
					<th data-options="field:'leaveDate'" width="12">离职时间</th>
					<th data-options="field:'supplier'" width="10">供应商</th>
					<th data-options="field:'currentState'" width="10">当前状态</th>
					<th data-options="field:'technicalDirection'" width="10">技术方向</th>
					<th data-options="field:'technicalLevel'" width="10">技术级别</th>
					<th data-options="field:'departmentId'" width="10" hidden="true">部门ID</th>
					<th data-options="field:'position'" width="10" >职务</th>
					<th data-options="field:'payDepartment'" width="10" >付费部门</th>
					<th data-options="field:'baseMoney'" width="10" hidden="true">基本工资</th>
					<th data-options="field:'overtime'" width="10" hidden="true">加班费</th>
					<th data-options="field:'age'" width="10" hidden="true">工龄工资</th>
					<th data-options="field:'check1'" width="10" hidden="true">考勤费</th>
					<th data-options="field:'absent'" width="10" hidden="true">旷工费</th>
					<th data-options="field:'safety'" width="10" hidden="true">保险费</th>
				</tr>
			</thead>
	</table>
	<div id="tb" >
	<%
	   String role = (String)session.getAttribute("role");
	   if ("0".equalsIgnoreCase(role)) {/*0代表超级管理员  */
	%>
	   <div>
	   	<a href="javascript:openEmployeeAddDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true">添加</a>
	   	<a href="javascript:openEmployeeModifyDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" plain="true">修改</a>
	   	<a href="javascript:deleteEmployee()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" plain="true">删除</a>
	   	<a href="javascript:exportEmployee()" class="easyui-linkbutton" data-options="iconCls:'icon-export'" plain="true">导出Execl</a>
	   </div>	
	<%
	      }
	%>	
		<div>
			<form id="search" method="post" >
				&nbsp;编号:&nbsp;<input type="text" name="s_employeeNo" id="s_employeeNo" size="10" />
			    &nbsp;姓名:&nbsp;<input type="text" name="s_name" id="s_name" size="10" />
				&nbsp;性别:&nbsp;<select class="easyui-combobox" id="s_sex" name="s_sex" editable="false" panelHeight="auto" >
									<option value="" >请选择</option>
									<option value="男" >男</option>
									<option value="女" >女</option>
								</select>
				&nbsp;出生日期:&nbsp;<input type="text" class="easyui-datebox" name="s_bbirthday" id="s_bbirthday" size="11" editable="false" />
									<input type="text" class="easyui-datebox" name="s_ebirthday" id="s_ebirthday" size="11" editable="false" />
				<!-- &nbsp;民族:&nbsp;<input type="text" name="s_nationality" id="s_nationality" size="10" /> -->
				&nbsp;学历:&nbsp;<input type="text" name="s_education" id="s_education" size="10" />		
				&nbsp;所在部门:&nbsp;<input class="easyui-combobox" id="s_departmentId" name="s_departmentId" size="8" panelHeight="auto" data-options="editable:false,valueField:'departmentId',textField:'departmentName',url:'department!departmentComboList'" />	
				&nbsp;所在项目组:&nbsp;<input type="text" name="s_productLine" id="s_productLine" size="10" />				
				&nbsp;供应商:&nbsp;<input type="text" name="s_supplier" id="s_supplier" size="10" />				
				&nbsp;当前状态:&nbsp;<select class="easyui-combobox" id="s_currentState" name="s_currentState" editable="false" panelHeight="auto" >
									<option value="" >请选择</option>
									<option value="在职" >在职</option>
									<option value="离职" >离职</option>
								</select>
				&nbsp;入职时间:&nbsp;<input type="text" class="easyui-datebox" name="s_hiredate" id="s_hiredate" size="11" editable="false" />
				&nbsp;离职时间:&nbsp;<input type="text" class="easyui-datebox" name="s_leaveDate" id="s_leaveDate" size="11" editable="false" />
				&nbsp;技术方向:&nbsp;<input type="text" name="s_technicalDirection" id="s_technicalDirection" size="10" />				
				&nbsp;技术级别:&nbsp;<input type="text" name="s_technicalLevel" id="s_technicalLevel" size="10" />				
				&nbsp;职务:&nbsp;<input type="text" name="s_position" id="s_position" size="10" />				
				&nbsp;付费部门:&nbsp;<input type="text" name="s_payDepartment" id="s_payDepartment" size="10" />				
				<a href="javascript:searchEmployee()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" plain="true">搜索</a>
			</form>
		</div>
		<br />
	</div>
	<div id="dlg" class="easyui-dialog" style="width:400px;height:250px;padding:20px 10px 0;" closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post" >
			<table>
				<tr>
					<td>&nbsp;编号:&nbsp;</td>
					<td><input type="text" name="employee.employeeNo" id="employeeNo" class="easyui-validatebox" required="true" ></td>
					<td>&nbsp;付费部门:&nbsp;</td>
					<td><input type="text" name="employee.payDepartment" id="payDepartment" class="easyui-validatebox" required="true"></td>
				</tr>			
				<tr>
					<td>&nbsp;姓名:&nbsp;</td>
					<td><input type="text" name="employee.name" id="name" class="easyui-validatebox" required="true" ></td>
					<td>&nbsp;性别:&nbsp;</td>
					<td><select class="easyui-combobox" name="employee.sex" id="sex"  editable="false" panelHeight="auto" style="width:85px;">
								<option value="" >请选择</option>
								<option value="男" >男</option>
								<option value="女" >女</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;出生日期:&nbsp;</td>
					<td><input type="text" class="easyui-datebox" name="employee.birthday" id="birthday" editable="false"  /></td>
					<td>&nbsp;民族:&nbsp;</td>
					<td><input type="text" name="employee.nationality" id="nationality" class="easyui-validatebox" ></td>
				</tr>
				<tr>
					<td>&nbsp;学历:&nbsp;</td>
					<td><input type="text" name="employee.education" id="education" class="easyui-validatebox"  ></td>
					<td>&nbsp;专业:&nbsp;</td>
					<td><input type="text" name="employee.profession" id="profession" class="easyui-validatebox"  ></td>
				</tr>
				<tr>
					<td>&nbsp;所在部门:&nbsp;</td>
					<td><input class="easyui-combobox" id="departmentId" name="employee.departmentId" size="8" panelHeight="auto" data-options="editable:false,valueField:'departmentId',textField:'departmentName',url:'department!departmentComboList'" /></td>
					<td>&nbsp;所在项目组:&nbsp;</td>
					<td><input type="text" name="employee.productLine" id="productLine" class="easyui-validatebox" required="true" ></td>
				</tr>
				<tr>
					<td>&nbsp;供应商:&nbsp;</td>
					<td><input type="text" name="employee.supplier" id="supplier" class="easyui-validatebox" required="true" ></td>
					<td>&nbsp;职务:&nbsp;</td>
					<td><input type="text" name="employee.position" id="position" class="easyui-validatebox" ></td>
				</tr>
				<tr>
					<td>&nbsp;当前状态:&nbsp;</td>
					<td><select class="easyui-combobox" name="employee.currentState" id="currentState"  editable="false" panelHeight="auto" style="width:85px;">
								<option value="" >请选择</option>
								<option value="在职" >在职</option>
								<option value="离职" >离职</option>
						</select>
					</td><td>&nbsp;入职时间:&nbsp;</td>
					<td><input type="text" name="employee.hiredate" id="hiredate" class="easyui-datebox" editable="false" required="true" ></td>
				</tr>
				<tr>
					<td>&nbsp;身份证:&nbsp;</td>
					<td><input type="text" name="employee.idcardno" id="idcardno" class="easyui-validatebox"  ></td>
					<td>&nbsp;联系电话:&nbsp;</td>
					<td><input type="text" name="employee.phone" id="phone" class="easyui-validatebox"  ></td>
				</tr>
				<tr>
					<td>&nbsp;技术方向:&nbsp;</td>
					<td><input type="text" id="technicalDirection" name="employee.technicalDirection" class="easyui-validatebox" required="true" /></td>
					<td>&nbsp;技术级别:&nbsp;</td>
					<td><input type="text" name="employee.technicalLevel" id="technicalLevel" class="easyui-validatebox" required="true" ></td>
				</tr>
				<tr>
					<td>&nbsp;离职时间:&nbsp;</td>
					<td><input type="text" name="employee.leaveDate" id="leaveDate" class="easyui-datebox" editable="false" ></td>	
					<td></td>
					<td></td>
				</tr>
				<tr>
					<input type="text" name="employee.baseMoney" id="baseMoney" class="easyui-validatebox" hidden="true">
					<input type="text" name="employee.overtime" id="overtime" class="easyui-validatebox" hidden="true" >
					<input type="text" name="employee.age" id="age" class="easyui-validatebox" hidden="true">
					<input type="text" name="employee.check1" id="check1" class="easyui-validatebox" hidden="true">
					<input type="text" name="employee.absent" id="absent" class="easyui-validatebox" hidden="true">
					<input type="text" name="employee.safety" id="safety" class="easyui-validatebox" hidden="true">
				</tr>
			</table>			
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:saveEmployee()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
		<a href="javascript:closeEmployeeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
	</div>
</body>
</html>