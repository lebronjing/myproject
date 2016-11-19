package com.self.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.self.Dto.TreeDto;
import com.self.IDao.TreeMapper;
import com.self.domain.Tree;
import com.self.service.DepartmentService;

//@Service("departmentServiceImpl")
@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private TreeMapper treeMMapper;
	
	public void save(Tree tree) {
		treeMMapper.insert(tree);
	}

	public void update(Tree tree) {
		treeMMapper.updateByPrimaryKeySelective(tree);
	}

	public void saveOrUpdate(Tree tree) {
		
	}

	public void delete(String id) {
		treeMMapper.deleteByPrimaryKey(id);
	}

	public List<Tree> findByTreeType(String typeId) {
		return treeMMapper.findByTreeType(typeId);
	}

	public Tree findById(String id) {
		return treeMMapper.selectByPrimaryKey(id);
	}
	/**
	 * 更新排序
	 * **/
	public String updateLibrarySort(TreeDto dto, String loginName) {
		String flag = "success";
		//目标分类信息
		Tree targetTree = treeMMapper.selectByPrimaryKey(dto.getTargetId());
		//拖动分类信息
		Tree tree = treeMMapper.selectByPrimaryKey(dto.getId());
		
		tree.setCreateTime(new Date());
		tree.setCreateBy(loginName);
		targetTree.setCreateTime(new Date());
		targetTree.setCreateBy(loginName);
		Map<String, Object> treeMap = new HashMap<String, Object>();
		if(dto.getMoveType().equals("prev")){//向上移动
			treeMap.put("pid", tree.getPid());
			treeMap.put("orderId", tree.getOrderId());
			treeMap.put("targetOrderId", targetTree.getOrderId());
			List<Tree> treeList = treeMMapper.findTreeListByOrderId(treeMap);
			String orderId = String.valueOf(Integer.valueOf(targetTree.getOrderId()) + 1);
			tree.setOrderId(targetTree.getOrderId()); 
			targetTree.setOrderId(orderId);
			for (int i = 0; i < treeList.size(); i++) {//更新所有受影响的排序字段
				Tree tr = treeList.get(i);
				if(!tr.getId().equals(tree.getId()) && !tr.getId().equals(targetTree.getId())){
					tr.setUpdateTime(new Date());
					tr.setUpdateBy(loginName);
					tr.setOrderId(String.valueOf(Integer.valueOf(tr.getOrderId()) + 1));
					treeMMapper.updateByPrimaryKey(tr);
				}
				treeMMapper.updateByPrimaryKey(tree);
				treeMMapper.updateByPrimaryKey(targetTree);
				flag = "success";
			}
		}else if(dto.getMoveType().equals("next")){//向后移动 
			treeMap.put("pid", tree.getPid());
			treeMap.put("orderId", targetTree.getOrderId());
			treeMap.put("targetOrderId", tree.getOrderId());
			List<Tree> treeList = treeMMapper.findTreeListByOrderId(treeMap);
			tree.setOrderId(targetTree.getOrderId()); 
			for (int i = 0; i < treeList.size(); i++) {//更新所有受影响的排序字段
				Tree tr = treeList.get(i);
				if(!tr.getId().equals(tree.getId())){
					tr.setUpdateTime(new Date());
					tr.setUpdateBy(loginName);
					tr.setOrderId(String.valueOf(Integer.valueOf(tr.getOrderId()) - 1));
					treeMMapper.updateByPrimaryKey(tr);
				}
				treeMMapper.updateByPrimaryKey(tree);
				flag = "success";
			}
		}
		return flag;
	}

	public Tree findMaxOrder(String typeId) {
		List<Tree> list = treeMMapper.findMaxOrder(typeId);
		Tree tree = new Tree();
		if(list.size() > 0){
			tree = list.get(0);
		}
		return tree;
	}
	
}
