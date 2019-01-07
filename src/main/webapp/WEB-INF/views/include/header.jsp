<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- top menu start -->
<style>
.pcoded .pcoded-header[header-theme="theme4"] {
	background: #f2f2f2;
}
.header-navbar .navbar-wrapper .navbar-logo a {
	color: #5d5d5d;
}
.header-navbar .navbar-wrapper .navbar-container .nav-left a, .header-navbar .navbar-wrapper .navbar-container .nav-right a {
	color: #47494b;
	padding: 1.5rem .6rem;
}
</style>

<nav class="navbar header-navbar pcoded-header" header-theme="theme4">
        <div class="navbar-wrapper" style="background: red;">
          <div class="navbar-logo">
            <a class="mobile-menu" id="mobile-collapse" href="#!">
              <i class="ti-menu"></i>
            </a>
	      <a href="../main/main">
	        <img class="img-fluid" src="../assets/images/starwatch_logo.png" alt="Theme-Logo" width="120"/>
	      </a>
	      <a class="mobile-options">
	      	<i class="ti-more"></i>
	      </a>
    </div>
     <div class="navbar-container container-fluid">
            <div>
              <ul class="nav-left">
                <li>
             <div class="sidebar_toggle"><a href="javascript:void(0)"><i class="ti-menu"></i></a></div>
                </li>
        </ul>
        <ul class="nav-right">
            <li class="user-profile header-notification">
                <form id='logoutForm' action="../logoutAction" method="post">
                	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                  		<a onclick='$("#logoutForm").submit();' style="cursor:pointer;">
                    <i class="ti-layout-sidebar-left"></i> Logout
                  </a>
                </form>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </div>
</nav>

<%-- <script type="text/javascript">
	$(document).ready(function(){
		$("#logoutBtn").on("click", function(){
			$("#logoutForm").submit();
		});
	})
</script> --%>
<!-- top menu end -->
