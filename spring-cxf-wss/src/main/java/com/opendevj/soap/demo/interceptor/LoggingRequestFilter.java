package com.opendevj.soap.demo.interceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingRequestFilter implements Filter {

	private Tracer tracer;
	
	public LoggingRequestFilter( Tracer tracer ) {
		this.tracer = tracer;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		long start = System.currentTimeMillis();
		
		try {
			String traceId = (tracer != null && tracer.currentSpan() != null) 
					? Long.toHexString(tracer.currentSpan().context().traceId()) 
							: UUID.randomUUID().toString(); 
			MDC.put("traceId", traceId);
			MDC.put("ipAddress", httpRequest.getRemoteAddr());
			
			log.debug("Start processing request: [{}]", getRequestURL(httpRequest));
			
			chain.doFilter(request, response);
		} finally {
			log.debug("End processing request: [{}] : [{}ms]", getRequestURL(httpRequest), System.currentTimeMillis() - start);
			
			MDC.clear();
		}
	}
	
	private String getRequestURL(HttpServletRequest request) {
		if (request != null) {
			return request.getRequestURL().toString();
		}
		return "";
	}
	
	
	@SuppressWarnings("unused")
	private String getRequestType (HttpServletRequest request) {
		if (request != null && StringUtils.hasText(request.getPathInfo())) {
			List<String> parts = Arrays.asList(request.getPathInfo().split("/"));
			if (parts.size() > 2)
				return parts.get(2);
		}
		return "";
	}

}
