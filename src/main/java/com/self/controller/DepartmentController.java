package com.self.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.json.JsonArray;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.self.Dto.HighchartsData;
import com.self.Dto.TreeDto;
import com.self.domain.Tree;
import com.self.service.DepartmentService;

@Controller
@RequestMapping("/")
public class DepartmentController {
	//@Resource(name="departmentServiceImpl")
	@Autowired
	private DepartmentService departmentService;
	
	//查询树
	@RequestMapping(value="departmentManage.do")
	public ModelAndView department(){
		List<Tree> list = departmentService.findByTreeType("1");
		List<TreeDto> treeList = new ArrayList<TreeDto>();
		for (Tree tree : list) {
			TreeDto treeDto = new TreeDto(tree.getId(), tree.getPid(), tree.getName(), tree.getPid().equals("0")?true:false, true);
			treeList.add(treeDto);
		}
		
		
		String treeJson = JSON.toJSONString(treeList);
		return new ModelAndView("ztree/ztree","data",treeJson);
	}
	@RequestMapping(value="saveTree.do")
	@ResponseBody
	public String saveTree(HttpServletRequest request,TreeDto dto){
		String loginName = (String) request.getSession().getAttribute("name");
		Tree maxT = departmentService.findMaxOrder("1");
		UUID uuid = UUID.randomUUID();
		Tree tree = new Tree();
		tree.setId(uuid.toString());
		tree.setName(dto.getName());
		tree.setPid(dto.getpId());
		tree.setCreateTime(new Date());
		tree.setCreateBy(loginName);
		if(maxT != null){
			tree.setOrderId(String.valueOf(Integer.valueOf(maxT.getOrderId()) + 1));
		}else{
			tree.setOrderId("1");
		}
		tree.setIsDelete("0");
		tree.setLevelId(dto.getLevel());
		tree.setTreeType("1");
		departmentService.save(tree);
		return "success";
	}
	
	//修改树节点
	@RequestMapping(value="updateTree.do")
	@ResponseBody
	public String  updateTree(TreeDto dto){
		Tree tree = departmentService.findById(dto.getId());
		tree.setName(dto.getName());
		departmentService.update(tree);
		return "success";
	}
	
	//删除树节点
	@RequestMapping(value="deleteTree.do")
	@ResponseBody
	public String deleteTree(String id){
		departmentService.delete(id);
		return "success";
	}
	//树拖曳排序
	@RequestMapping(value="updateTreeSort.do")  
	@ResponseBody  
	public String updateLibrarySort(HttpServletRequest request,TreeDto dto) {  
		String loginName = (String) request.getSession().getAttribute("name"); 
	    return departmentService.updateLibrarySort(dto,loginName); 
	}
	
	
	@RequestMapping(value="highcharts.do")
	public ModelAndView highChars(){
		return new ModelAndView("highcharts/highcharts", "data", null);
	}
	
	@RequestMapping(value="getHighcharsData.do")
	@ResponseBody
	public String getHighCharts(){
		/*List data = new ArrayList();
		for(int i = 1; i < 10; i++){
			data.add(i);
		}
		List ll = new ArrayList();
		for(int i=0; i <10; i++){
			ll.add("\"data"+i+"\"");
		}
		String str = "";
		str += "{\"title\":\"this is demo\",\"data\":"+data+",\"xAxis\":"+ll+"}";*/
		//String str = "{'name':'Tokyo','data':[7.0,6.9,9.5,14.5,18.4,21.5,25.2,26.5,23.3,18.3,13.9,9.6]},{'name':'London','data':[3.9,4.2,5.7,8.5,11.9,15.2,17.0,16.6,14.2,10.3,6.6,4.8]}";
		
		
		long[] k=new long[] {7,6,9,14,18,21,25,26,23,18,13,9};
		long[] k1=new long[] {1,5,2,11,13,18,15,19,2,8,3,25};
		
		List<HighchartsData> list = new ArrayList<HighchartsData>();
		HighchartsData data = new HighchartsData();
		data.setName("Tokyo");
		data.setData(k);
		HighchartsData data1 = new HighchartsData();
		data1.setName("London");
		data1.setData(k1);
		list.add(data);
		list.add(data1);
		String jsonString = JSON.toJSONString(list);
		System.out.println(jsonString.toString());
		return jsonString;
	}
	
}
