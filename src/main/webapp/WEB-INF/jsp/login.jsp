<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<script type="text/javascript" src="../fpage/js/jQuery.js"></script>
<script type="text/javascript" src="../fpage/js/fun.base.js"></script>
<script type="text/javascript" src="../fpage/js/script.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2_min.js"></script>
<link rel="stylesheet" type="text/css" href="../fpage/css/main.css">
</head>
<body>
	<div class="login">
		<div class="box png">
			<div class="logo png"></div>
			<div class="input">
				<div class="log">
					<form class="logform" action="" method="post">
						<div id="msgs"
							style="color: red; position: absolute; top: 75px; left: 180px;">${msg}</div>
						<div class="name">
							<label>帐&nbsp;&nbsp;&nbsp;号</label><input type="text"
								class="text" name="username"  datatype="s4-18" nullmsg="请输入帐号！"/>
						</div>
						<div class="pwd">
							<label>密&nbsp;&nbsp;&nbsp;码</label><input type="password"
								class="text" name="password"  datatype="*6-16" nullmsg="请输入密码！"/>
							<p style="text-align: center; line-height: 45px">
								<input type="checkbox" name="rememberMe" />&nbsp;&nbsp;记住我
							</p>
							<input type="submit" class="submit" tabindex="3" value="登录" />
							<input type="reset"  class="reset" value="重 置" />
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="air-balloon ab-1 png"></div>
		<div class="air-balloon ab-2 png"></div>
		<div class="footer"></div>
	</div>
	<div
		style="text-align: center; margin: 50px 0; font: normal 14px/24px 'MicroSoft YaHei';">
	</div>
<script type="text/javascript">
$(function(){
	//$(".registerform").Validform();  //就这一行代码！;
		
	$(".logform").Validform({
		tiptype:function(msg,o,cssctl){
			var objtip=$("#msgs");
			cssctl(objtip,o.type);
			objtip.text(msg);
		},
		tipSweep:true
		//ajaxPost:true
	});
})
</script>

</body>
</html>