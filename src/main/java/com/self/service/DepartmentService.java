package com.self.service;

import java.util.List;

import com.self.Dto.TreeDto;
import com.self.domain.Tree;

public interface DepartmentService {
	public void save(Tree tree);
	public void update(Tree tree);
	public void saveOrUpdate(Tree tree);
	public void delete(String id);
	public List<Tree> findByTreeType(String typeId);
	public Tree findById(String id);
	public String updateLibrarySort(TreeDto dto, String loginName);
	public Tree findMaxOrder(String typeId);
}
