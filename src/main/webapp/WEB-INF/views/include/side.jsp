<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- left menu start -->
<style>
.pcoded .pcoded-navbar[active-item-theme="theme4"] .pcoded-item > li.active > a:before {
    border-left-color: #70cef9 !important;
}
.pcoded .pcoded-navbar[active-item-theme="theme4"] .pcoded-item li.pcoded-hasmenu.active > a {
    background: #70cef9 !important;
}
.pcoded .pcoded-navbar[active-item-theme="theme4"] .pcoded-item li:hover > a {
    color: #70cef9 !important;
}
.pcoded .pcoded-navbar[navbar-theme="theme4"] .main-menu .main-menu-header {
    background-color: #818285;
}
</style>
<nav class="pcoded-navbar" pcoded-header-position="relative">
  <div class="pcoded-inner-navbar main-menu">
    <div class="">
      <div class="main-menu-header">
        <div class="user-details">
          <span id="more-details" style="margin-left: 10px;">
          <img alt="naver" src="../assets/images/union_contents.png" style="width: 136px; height: 60px; display: block; cursor:auto; margin-left: auto; margin-right: auto;">
          </span>
        </div>
        <!-- <div class="user-details">
          <span id="more-details">Union Content</span>
        </div> -->
      </div>
    </div>
    <ul class="pcoded-item pcoded-left-item">
	    <li class="main">
		    <a href="../main/main">
		    <span class="pcoded-micon"><i class="ti-dashboard"></i></span>
		    <span class="pcoded-mtext">모니터링현황</span>
		    <span class="pcoded-mcaret"></span>
		    </a>
	    </li>
	    <li class="statistics">
		    <a href="../statistics/statistics">
			    <span class="pcoded-micon"><i class="ti-stats-up"></i></span>
			    <span class="pcoded-mtext">통계현황</span>
			    <span class="pcoded-mcaret"></span>
		    </a>
	    </li>
    </ul>
  </div>
</nav>
<script type="text/javascript">

		// 해당 페이지 active 추가
		var url = location.href;

		var domain = url.split("9090/")[1].split("/")[0];

		var li = window.document.getElementsByClassName(domain)[0];
		
		console.log(li);
		if(li != undefined){
			li.className += " active pcoded-trigger";
		}
		
	
</script>
<!-- left menu end -->
