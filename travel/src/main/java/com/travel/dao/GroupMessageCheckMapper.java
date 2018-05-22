package com.travel.dao;

import com.travel.bean.GroupMessageCheck;
import com.travel.bean.GroupMessageCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupMessageCheckMapper {
    long countByExample(GroupMessageCheckExample example);

    int deleteByExample(GroupMessageCheckExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupMessageCheck record);

    int insertSelective(GroupMessageCheck record);

    List<GroupMessageCheck> selectByExample(GroupMessageCheckExample example);

    GroupMessageCheck selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupMessageCheck record, @Param("example") GroupMessageCheckExample example);

    int updateByExample(@Param("record") GroupMessageCheck record, @Param("example") GroupMessageCheckExample example);

    int updateByPrimaryKeySelective(GroupMessageCheck record);

    int updateByPrimaryKey(GroupMessageCheck record);
}