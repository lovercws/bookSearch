<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isErrorPage="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body bgcolor="#ffffff">
    <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="table2">
      <tr>
        <td width="100%" height="100" align="center" valign="bottom" class="td2">
          <table width="100%" border="0" cellspacing="5" cellpadding="6">
            <tr>
              <td colspan="2">系统异常,请尝试以下操作:</td>
            </tr>
            <tr>
              <td height="1" colspan="2" align="right" bgcolor="#006699"></td>
            </tr>
            <tr>
              <td height="3" align="right"></td>
              <td width="602" height="3"></td>
            </tr>
            <tr>
              <td width="65" align="center">
                <p>
                  <img src="<%=request.getContextPath()%>/images/icons/icoInfo.gif" width="48" height="48">
                </p>
              </td>
              <td>
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="4">
                  <tr>
                    <td width="50" align="right">
                      <font color="#006699">1、</font>
                    </td>
                    <td>
                      <font color="#006699">[<a href="javascript:history.back();">尝试</a>] 重试您的操作</font>
                    </td>
                  </tr>
                  <tr>
                    <td width="50" align="right"><font color="#006699">2、</font></td>
                    <td>
                      <font color="#006699">[<a href="<%=request.getContextPath()%>" target="_parent">尝试</a>] 重新登录 </font>
                    </td>
                  </tr>
                  <tr>
                    <td width="50" align="right"><font color="#006699">3、</font></td>
                    <td><font color="#006699">[<a href="javascript:window.close();">尝试</a>] 关闭浏览器，重试您的操作</font></td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td align="center" valign="bottom" class="td2">
          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="4">
            <tr valign="bottom">
              <td height="33" colspan="2">请将以下系统内部错误描述报告给系统管理员或其他相关人员:</td>
            </tr>
            <tr bgcolor="#FF0000"><td height="1" colspan="2"></td></tr>
            <tr>
              <td width="15%" align="right">错误信息:</td>
              <td width="85%" style="color:#FF0000">
              <%=exception.getMessage()!=null ? exception.getMessage() : request.getAttribute("_CommandExecuteException_")%>
              </td>
            </tr>
            <tr>
              <td align="right" valign="top">详细信息:</td>
              <td onclick="swcCause(this);" style="cursor:hand" title="收起/展开">&lt;&lt;&lt;</td>
            </tr>
            <tr id="trCause">
              <td colspan="2" align="right" valign="top">
              <textarea name="textarea" rows="15" style="color:#FF0000;width:100%">
              	<%=exception.getLocalizedMessage()%>
              </textarea>
              </td>
            </tr>
    </table></td>
  </tr>
</table>
</body>
<SCRIPT LANGUAGE="JavaScript">
function swcCause(el) {
	var trCause = document.getElementById("trCause");
	if (trCause.style.display == "none") {
		trCause.style.display = "";
		el.innerText = "<<<";
	} else {
		trCause.style.display = "none";
		el.innerText = ">>>";
	}
}
</SCRIPT>
</html>