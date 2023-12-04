package com.docmall.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.docmall.demo.domain.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean result = false;
		
		// 현재 클라이언트의 세션을 통한 인증상태 확인작업을 할수가 있다.
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO) session.getAttribute("loginStatus");
		
		if(user == null) { //인증정보가 없는 경우
			
			result = false;  // 컨트롤러로 진행을 하지 못한다.
			
			if(isAjaxRequest(request)) {
				
				response.sendError(400); // 클라이언트의 ajax구문에서 error : 동작됨.
			}else {
				getTargetUrl(request);
				response.sendRedirect("/member/login");
			}
			
		}else {		// 인증정보가 있는 경우. 즉 세션 loginStatus 정보가 존재.(사용자는 로그인중)
			
			result = true; // 컨트롤러로 진행을 하게된다.
		}
		
		return result;
	}

	//인증되지 않는 상태에서 사용자가 요청한 URL을 저장하고, 로그인 후 URL로 리다이렉트 작업목적때문.
	private void getTargetUrl(HttpServletRequest request) {
		
		//요청주소.  localhost:9090/member/modify?userid=doccomsa
		
		String uri = request.getRequestURI(); //   /member/modify
		String query = request.getQueryString();  //  userid=doccomsa
		
		if(query == null || query.equals("null")) {
			query = "";
		}else {
			query = "?" + query;
		}
		
		String targetUrl = uri + query;
		
		if(request.getMethod().equals("GET")) {
			request.getSession().setAttribute("targetUrl", targetUrl);
			
		}
		
	}
	
	// 사용자 요청이 ajax요청인지 체크
	private boolean isAjaxRequest(HttpServletRequest request) {
		
		boolean isAjax = false;
		
		String header = request.getHeader("AJAX");
		if(header != null && header.equals("true")) {
			isAjax = true;
		}
		
		return isAjax;
	}

	
}
