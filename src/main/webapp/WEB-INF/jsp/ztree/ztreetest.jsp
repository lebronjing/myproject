<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" href="css/ztreecss/demo.css" type="text/css">
	<link rel="stylesheet" href="css/ztreecss/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
	<script type="text/javascript" src="js/ztreejs/jquery.ztree.all.min.js"></script>
	
	<SCRIPT type="text/javascript">
	var dragId;
		var setting = {
			view: {
				addHoverDom: addHoverDom,//当鼠标移动到节点上时，显示用户自定义控件
				removeHoverDom: removeHoverDom,//当鼠标移出节点时，隐藏用户自定义控件
				selectedMulti: false//设置是否允许同时选中多个节点。
			},
			edit: {
				drag: {
					prev: true,//拖拽到目标节点时，设置是否允许移动到目标节点前面的操作
					next: true,//拖拽到目标节点时，设置是否允许移动到目标节点后面的操作
					inner: false//拖拽到目标节点时，设置是否允许成为目标节点的子节点
				},
				enable: true,//设置 zTree 是否处于编辑状态
				editNameSelectAll: false,//设置是否为全选状态
				removeTitle: "删除节点",//删除按钮的Title 辅助信息
				renameTitle: "编辑节点名称",//编辑按钮的Title 辅助信息
				showRemoveBtn: setRemoveBtn,//设置是否显示删除按钮
			},
			data: {
				keep: {
					leaf: false,//节点叶子节点属性锁
					parent: false//节点父节点属性锁
				},
				simpleData: {
					enable: true,//是否采用简单数据模式 (Array)
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			},
			async: {
				enable: true,//是否开启异步加载模式
				autoParam: ["id","name"]//异步加载时需要自动提交父节点属性的参数
			},
			callback: {
				beforeEditName: beforeEditName,//编辑回调函数
				beforeRename: beforeRename,//删除回调函数
				beforeRemove: beforeRemove,//编辑结束（Input失去焦点或按下Enter键）之后，更新节点名称数据之前的事件回调函数
				beforeDrag: beforeDrag,//拖曳前执行
				beforeDrop: beforeDrop,//拖曳后执行
				beforeClick: beforeClick,//用于捕获单击节点之前的事件回调函数
				onRemove: onRemove,//用于捕获删除节点之后的事件回调函数
				onRename: onRename,//用于捕获节点编辑名称结束之后的事件回调函数
			}
		};
		function beforeClick(treeId, treeNode, clickFlag) {
			alert("点击触发");
		};
		function onRemove(event, treeId, treeNode) {
			alert(treeNode.tId + ", " + treeNode.name);
		}
		function onRename(event, treeId, treeNode, isCancel) {
			alert(treeNode.tId + ", " + treeNode.name);
		}
		
		function setRemoveBtn(treeId, treeNode) {
			if(treeNode.level == 0)//判断为顶级节点则不显示删除按钮
			return false;
			else
			return true;
		}
		function beforeRename(treeId, treeNode, newName, isCancel){
			if(newName.length == 0){
				alert("不能为空");
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				setTimeout(function(){zTree.editName(treeNode)}, 10);
				return false;
			}
			return true;
		}
		function beforeRemove(treeId, treeNode){
			alert("is delete?");
			return true;
		}
		
		
		function beforeDrag(treeId, treeNodes){
			for(var i=0;i<treeNodes.length;i++){
				dragId = treeNodes[i].pId;
				if(treeNodes[i].drag === false){
					return false;
				}
			}
			return true;
		}
		function beforeDrop(treeId, treeNodes, targetNode, moveType){
			if(targetNode.pId == dragId){
				//id 拖曳id， targetId 目标id， moveType 移动类型
				var data = {id: treeNodes[0].id,targetId: targetNode.id,moveType: moveType};
				var confirmVal = false;
				$.ajax({
					async: false,
					url:'<%=request.getContextPath()%>/updateTreeSort.htm',
					method:'post',
					dataType:'json',
					data:data, 
					success:function(data){
						if(data == "success"){
							confirmVal = true;
						}else{
							alert("操作失败");
						}
					}
				})
				return confirmVal;
			}else {
				alert("亲，只能进行同级排序！");
				return false;
			}
		}
		
		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var id = getUuid();
			var sObj = $("#" + treeNode.tId + "_span");
			//treeNode.level == 3 设置第四级没有添加功能
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0 || treeNode.level == 3) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='增加节点名称' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				
				
				zTree.addNodes(treeNode, {id:id, pId:treeNode.id, name:"新增XXX" + (newCount++)});
				alert("保存数据库");
				
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		
		
		function getUuid() {
		    function S4() {
		       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
		    }
		    return (S4()+S4()+S4()+S4()+S4()+S4()+S4()+S4());
		}
		//采用简单数据模式(Array)
		var zNodes =[
			{ id:1, pId:0, name:"父节点 1", open:true},
			{ id:11, pId:1, name:"叶子节点 1-1",checked:true},
			{ id:111, pId:11, name:"叶子节点 11-1",checked:true},
			{ id:12, pId:1, name:"叶子节点 1-2"},
			{ id:13, pId:1, name:"叶子节点 1-3"}

		];

		function beforeEditName(treeId, treeNodes) {
			return true;
		}
		
		$(document).ready(function(){
			console.log('${data}');
			var znodess = '${data}';
			//备注：后台传过来的json数据一定执行这个操作eval("["+data+"]") 将String转换为对象
			$.fn.zTree.init($("#treeDemo"), setting, eval(znodess));
			//$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$("#add").bind("click", {isParent:true}, add);
		});
		
		function add(e){
			alert(e.data.isParent + " add test");
		}
	</SCRIPT>
	<style type="text/css">
		.mytable {
			table-layout: fixed;
		}
		.td{
			text-overflow: ellipsis;
			-mov-text-overflow: ellipsis;
			overflow: hidden;
			white-space: nowrap;
			border: 1px solid;
			text-align: left
			
		}
		.td:hover {
			text-overflow: inherit;
			overflow: visible;
			cursor: hand;
		}
		/**为tree增加添加按钮**/
		.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
  </head>
  
  <body>
    <div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	
	<c:forEach var="item" items="${model}" varStatus="status">   
      <tr> 
      	<td>
          ${item.name}
      	</td> 
      </tr>   
	</c:forEach> 
  </body>
</html>
