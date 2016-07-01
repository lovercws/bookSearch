<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源找不到</title>
</head>
<body>
	<table width="550" height="450" cellspacing="0" cellpadding="0"
		align="center" border="0">
		<tbody>
			<tr>
				<td valign="top" background="images/bg/page404.gif">
					<table cellspacing="0" cellpadding="0" width="100%" border="0">
						<tbody>
							<tr>
								<td height="350"></td>
							</tr>
							<tr>
								<td>
									<table cellspacing="2" cellpadding="0" width="100%"
										align="center" border="0">
										<tbody>
											<tr>
												<td align="center"
													style="font-size: 9pt; font-weight: bold;"><font
													color="#990000">[<a
														href="javascript:history.back();" title="返回前一步">返回</a>]
												</font>&nbsp;|&nbsp; <font color="#990000">[<a
														href="javascript:window.close();" title="关闭浏览器">关闭</a>]
												</font>&nbsp;|&nbsp; <font color="#990000">[<a
														href="<%=request.getContextPath()%>/portal/loginAction_goLogin.do" title="重新登录系统">重新登录</a>]
												</font></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>