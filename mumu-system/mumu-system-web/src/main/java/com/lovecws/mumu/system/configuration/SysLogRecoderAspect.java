package com.lovecws.mumu.system.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.log.MumuLog;
import com.lovecws.mumu.system.controller.system.message.SysMessageHandler;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.entity.SysUserLog;
import com.lovecws.mumu.system.service.SysUserLogService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @desc 将重要日志记录到服务器
 * @author ganliang
 * @version 2016年8月12日 下午3:17:24
 */
@Component("SysLogRecoderAspect")
@Aspect
public class SysLogRecoderAspect {

	private static final Logger log = Logger.getLogger(SysLogRecoderAspect.class);
	// gson
	private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	@Autowired
	private SysUserLogService userLogService;
	/*@Autowired
	private LogMessageSender logMessageSender;*/
	@Autowired
	private SysMessageHandler messageHandler;
	
	/**
	 * 全局controller 切面
	 */
	@Pointcut("execution(* com.lovecws.mumu.system.controller.system.*.*Controller.*(..))")
	public void SysLogRecoderAspect() {
	}

	@Around("SysLogRecoderAspect()")
	public Object globalAround(ProceedingJoinPoint joinPoint) throws Throwable {
		//将日志信息保存到数据库
		return handleAnnotationLog(joinPoint);
	}

	/**
	 * 处理注解MumuLog日志
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	private Object  handleAnnotationLog(ProceedingJoinPoint joinPoint) throws Throwable {
		//获取请求方法
		Method requestMethod = getMethod(joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), joinPoint.getArgs());
		if (requestMethod == null) {
			throw new RuntimeException("oh my god! it serious problem!");
		}
		requestMethod.setAccessible(true);

		Object proceed=null;
		//日志注解
		MumuLog log = requestMethod.getAnnotation(MumuLog.class);
		if (log != null && log.required()) {
			SysUserLog userLog = buildLog(joinPoint);
			userLog.setContent(log.name());
			userLog.setOperateType(log.operater());
			userLog.setUserLogStatus(PublicEnum.NORMAL.value());
			userLogService.addSysUserLog(userLog);
			proceed=userLog.getProceed();
		}else{
			proceed=joinPoint.proceed();
		}
		//handlerLoginLog(joinPoint,proceed);
		return proceed;
	}

	/**
	 * 处理登陆日志
	 * @param joinPoint
	 * @param proceed
	 */
	private void handlerLoginLog(ProceedingJoinPoint joinPoint,Object proceed) {
		Signature signature = joinPoint.getSignature();
		//是登陆操作
		if(signature.getDeclaringTypeName().equals("com.lovecws.mumu.system.controller.system.index.SysLoginController")&&signature.getName().equals("logining")){
			//登陆成功
			if(proceed instanceof ModelAndView){
				ModelAndView modelAndView=(ModelAndView)proceed;
				Map<String, Object> model = modelAndView.getModel();
				Object code = model.get("code");
				if(code!=null&&"200".equals(code.toString())){
					messageHandler.handler();
				}
			}
		}
	}


	/**
	 * 构建日志参数
	 * @param joinPoint
	 * @return
	 */
	private SysUserLog buildLog(ProceedingJoinPoint joinPoint) throws Throwable {
		SysUserLog userLog = new SysUserLog();
		try{
			Object principal = SecurityUtils.getSubject().getPrincipal();
			if (principal != null) {
				userLog.setCreator(SecurityUtils.getSubject().getPrincipal().toString());
			}
			SysUser user = (SysUser) SecurityUtils.getSubject().getSession(true).getAttribute(SysUser.SYS_USER);
			if (user != null) {
				userLog.setUserId(user.getUserId());
				userLog.setUserName(user.getUserName());
			}
			userLog.setIp(SecurityUtils.getSubject().getSession(true).getHost());
		}catch (Exception e){
			//TODO No SecurityManager accessible to the calling code, either bound to the org.apache.shiro.util.ThreadContext or as a vm static singleton.  This is an invalid application configuration.
		}
		userLog.setCreateTime(new Date());

		//将日志信息打印在控制台
		Signature signature = joinPoint.getSignature();
		String method=signature.getDeclaringTypeName() + " " + signature.getName();
		userLog.setMethod(method);

		//String parameter=StringUtils.join(joinPoint.getArgs(),",");
		StringBuilder builder=new StringBuilder();
		Object[] joinPointArgs = joinPoint.getArgs();
		for (Object arg:joinPointArgs){
			if(arg instanceof HttpServletRequest||arg instanceof HttpServletResponse){
				continue;
			}
			if(arg instanceof Serializable){
				builder.append(gson.toJson(arg)+",");
			}else{
				builder.append(arg+",");
			}
		}
		int lastIndexOf = builder.lastIndexOf(",");
		if(lastIndexOf>-1){
			builder.deleteCharAt(lastIndexOf);
		}
		userLog.setParameter(builder.toString());

		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long end = System.currentTimeMillis();
		String result=gson.toJson(proceed);
		userLog.setResult(result);
		userLog.setProceed(proceed);

		String usetime=(end - start) + "ms";
		userLog.setUsetime(usetime);
		return userLog;
	}
	/**
	 * 获取请求的方法
	 * @param clazz      字节码
	 * @param methodName 方法名称
	 * @param args       方法参数数组
	 * @return
	 */
	private Method getMethod(Class<? extends Object> clazz, String methodName, Object[] args) {
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for (Method method : declaredMethods) {
			method.setAccessible(true);
			if (method.getName().equals(methodName) && method.getParameters().length == args.length) {
				return method;
			}
		}
		return null;
	}
}
