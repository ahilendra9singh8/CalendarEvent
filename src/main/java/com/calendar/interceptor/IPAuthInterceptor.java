//package com.calendar.interceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.logging.Logger;
//
//@Component
//public class IPAuthInterceptor implements HandlerInterceptor {
//
//	private static final Logger LOGGER = Logger.getLogger(IPAuthInterceptor.class.getName());
//	private final Set<String> allowedIps = new HashSet<>();
//
//	public IPAuthInterceptor(@Value("${allowed.ips}") String allowedIpsConfig,
//							 @Value("${fetch.aws.ip}") boolean fetchAwsIp) {
//		allowedIps.addAll(Arrays.asList(allowedIpsConfig.split(",")));
//
//		if (fetchAwsIp) {
//			String awsPublicIP = getAWSPublicIP();
//			String awsPrivateIP = getAWSPrivateIP();
//
//			if (!awsPublicIP.isEmpty()) {
//				allowedIps.add(awsPublicIP);
//				LOGGER.info("AWS Public IP added: " + awsPublicIP);
//			}
//			if (!awsPrivateIP.isEmpty()) {
//				allowedIps.add(awsPrivateIP);
//				LOGGER.info("AWS Private IP added: " + awsPrivateIP);
//			}
//		}
//
//		LOGGER.info("Allowed IPs: " + allowedIps);
//	}
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		String clientIP = getClientIP(request);
//		LOGGER.info("Client IP: " + clientIP);
//		LOGGER.info("Allowed IPs: " + allowedIps);
//
//		if (!allowedIps.contains(clientIP)) {
//			LOGGER.warning("Access Denied for IP: " + clientIP);
//			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//			response.getWriter().write("Access Denied: Unauthorized IP - " + clientIP);
//			return false;
//		}
//		return true;
//	}
//
//	private String getClientIP(HttpServletRequest request) {
//		String ip = request.getHeader("X-Forwarded-For");
//		LOGGER.info("Checking 'X-Forwarded-For' header: " + ip);
//
//		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("X-Real-IP");
//			LOGGER.info("Checking 'X-Real-IP' header: " + ip);
//		}
//		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//			LOGGER.info("Using request.getRemoteAddr(): " + ip);
//		}
//		if ("0:0:0:0:0:0:0:1".equals(ip)) {
//			ip = "127.0.0.1"; // Convert localhost IPv6 to IPv4
//			LOGGER.info("Converted IPv6 localhost to IPv4: " + ip);
//		}
//		return ip;
//	}
//
//	private String getAWSPublicIP() {
//		try {
//			URL url = new URL("http://checkip.amazonaws.com");
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String awsPublicIP = reader.readLine().trim();
//			LOGGER.info("Fetched AWS Public IP: " + awsPublicIP);
//			return awsPublicIP;
//		} catch (Exception e) {
//			LOGGER.severe("Failed to fetch AWS Public IP: " + e.getMessage());
//			return "";
//		}
//	}
//
//	private String getAWSPrivateIP() {
//		try {
//			URL url = new URL("http://169.254.169.254/latest/meta-data/local-ipv4");
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String awsPrivateIP = reader.readLine().trim();
//			LOGGER.info("Fetched AWS Private IP: " + awsPrivateIP);
//			return awsPrivateIP;
//		} catch (Exception e) {
//			LOGGER.severe("Failed to fetch AWS Private IP: " + e.getMessage());
//			return "";
//		}
//	}
//}
//
