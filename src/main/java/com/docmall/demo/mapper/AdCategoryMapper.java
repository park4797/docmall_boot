package com.docmall.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.docmall.demo.domain.CategoryVO;

/*
 Mapper 인터페이스 대신 아래 형태로 사용을 함.
 interface AdCategoryDAO
 @Repository
 class AdCategoryDAOImpl
*/
//@Mapper 생략
public interface AdCategoryMapper {

	List<CategoryVO> getFirstCategoryList();
	
	List<CategoryVO> getSecondCategoryList(Integer cg_parent_code);
	
	CategoryVO get(Integer cg_code);
}
