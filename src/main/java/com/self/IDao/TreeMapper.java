package com.self.IDao;

import java.util.List;
import java.util.Map;

import com.self.domain.Tree;

public interface TreeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Tree record);

    int insertSelective(Tree record);

    Tree selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Tree record);

    int updateByPrimaryKey(Tree record);
    
    List<Tree> findByTreeType(String typeId);
    
    List<Tree> findTreeListByOrderId(Map<String,Object> tree);
    
    List<Tree> findMaxOrder(String typeId);
}