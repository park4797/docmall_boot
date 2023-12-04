package com.docmall.demo.domain;

import lombok.Data;

@Data
public class OrderDetailProductVO {

	// 기존클래스를 필드로 사용.  mybatis에서는 resultMap 사용해야 한다.
	private OrderDetailVO orderDetailVO;  // collection 으로 표현
	private ProductVO productVO;  // collection 으로 표현
}
