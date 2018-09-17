<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.md-tabs .nav-item, .md-tabs .main-menu .main-menu-content .nav-item .tree-1 a, .main-menu .main-menu-content .nav-item .tree-1 .md-tabs a, .md-tabs .main-menu .main-menu-content .nav-item .tree-2 a, .main-menu .main-menu-content .nav-item .tree-2 .md-tabs a, .md-tabs .main-menu .main-menu-content .nav-item .tree-3 a, .main-menu .main-menu-content .nav-item .tree-3 .md-tabs a, .md-tabs .main-menu .main-menu-content .nav-item .tree-4 a, .main-menu .main-menu-content .nav-item .tree-4 .md-tabs a {
    background-color: #fff;
    width: calc(100% / 2);
    text-align: center;
}
.nav-tabs .slide {
    background: #7cb5ec;
    width: calc(100% / 2);
    height: 4px;
    position: absolute;
    -webkit-transition: left 0.3s ease-out;
    transition: left 0.3s ease-out;
    bottom: 0;
}
</style>
</head>
<body>
	<div class="tab-header">
		<ul class="nav nav-tabs md-tabs tab-timeline" role="tablist" id="mytab">
			<a class="nav-item">
				<li id="media_match" class="nav-link" style="cursor:pointer;">
					<p>언론사통계</p>
					<div class="slide"></div>
			</li>
			</a>
			<a class="nav-item">
				<li id="reporter_match" class="nav-link" style="cursor:pointer;">
					<p>기자통계</p>
					<div class="slide"></div>
			</li>
			</a>
		</ul>
	</div>
</body>
</html>

<script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
  <script type="text/javascript" src="../bower_components/jquery-ui/jquery-ui.min.js"></script>
  <script type="text/javascript" src="../bower_components/tether/dist/js/tether.min.js"></script>
  <script type="text/javascript" src="../bower_components/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" >
	var selectOption = decodeURI(window.location.href.split("media/")[1]).split("?")[0];
	console.log(selectOption);
	
	if(selectOption == 'media_match'){
		$("#media_match").addClass('active');
		
	}else if(selectOption == 'reporter_match'){
		$("#reporter_match").addClass('active');
		
	}
	
	$(".nav-item").on("click", function(event){

		var part = event.currentTarget.children[0].id;

		var parameter = decodeURI(window.location.href.split("media/")[1]).split("?")[1];
		console.log("parameter: " + parameter);
		
		
		if(parameter == undefined){
			self.location = "../media/" + part;
		
		}else{
			
			if(parameter.indexOf("page=") >= 0){
				var first = parameter.split("page=")[0];
				var second = parameter.split("page=")[1];
				
				second = "1" + second.substr(1);
				
				parameter = first + "page=" + second;
			}
			
			
			
			self.location = "../media/" + part + "?" + parameter;
		}
		
	});
	
</script>