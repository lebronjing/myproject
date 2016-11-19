package com.self.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
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
}
