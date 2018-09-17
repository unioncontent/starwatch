<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="currTime" class="java.util.Date" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
  <title>starwatch</title>
  <!-- HTML5 Shim and Respond.js IE9 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
  <!-- Meta -->
  <meta charset="utf-8">
   <meta name="_csrf" content="${_csrf.token}" />
  <!-- default header name is X-CSRF-TOKEN -->
  <meta name="_csrf_header" content="${_csrf.headerName}"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="description" content="Phoenixcoded">
  <meta name="keywords" content="flat ui, admin , Flat ui, Admin , Responsive, Landing, Bootstrap, App, Template, Mobile, iOS, Android, apple, creative app">
  <meta name="author" content="Phoenixcoded">
  <!-- Favicon icon -->
  <link rel="icon" href="../assets/images/favicon.ico" type="image/x-icon">
  <!-- Google font-->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
  <!-- Required Fremwork -->
  <link rel="stylesheet" type="text/css" href="../bower_components/bootstrap/css/bootstrap.min.css">
  <!-- themify-icons line icon -->
  <link rel="stylesheet" type="text/css" href="../assets/icon/themify-icons/themify-icons.css">
  <!-- ico font -->
  <link rel="stylesheet" type="text/css" href="../assets/icon/icofont/css/icofont.css">
  <!-- font awesome -->
  <link rel="stylesheet" type="text/css" href="../assets/icon/font-awesome/css/font-awesome.css">
  <!-- Menu-Search css -->
  <link rel="stylesheet" type="text/css" href="../assets/pages/menu-search/css/component.css">
  <!-- Horizontal-Timeline css -->
  <link rel="stylesheet" type="text/css" href="../assets/pages/dashboard/horizontal-timeline/css/style.css">
  <!-- Date-range picker css  -->
  <link rel="stylesheet" type="text/css" href="../bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- Style.css -->
  <link rel="stylesheet" type="text/css" href="../assets/css/style.css">
  <link rel="stylesheet" type="text/css" href="../assets/css/picker.css">
  <link rel="stylesheet" type="text/css" href="../assets/pages/starwatch/style.css">
  <link rel="stylesheet" type="text/css" href="../assets/pages/period/css/style.css">
  <!--color css-->
  <link rel="stylesheet" type="text/css" href="../assets/css/color/color-1.css" id="color" />
  <link rel="stylesheet" type="text/css" href="../assets/css/linearicons.css">
  <link rel="stylesheet" type="text/css" href="../assets/css/simple-line-icons.css">
  <link rel="stylesheet" type="text/css" href="../assets/css/ionicons.css">
  <link rel="stylesheet" type="text/css" href="../assets/css/jquery.mCustomScrollbar.css">
  <style type="text/css">
	.layer{
		position:absolute;
		top:0;
		left:0;
		width:100%;
		height:100%;
		text-align:center;
		padding: 100px 0px;
	}
	.layer .content{
		display:inline-block;
		vertical-align:middle;
		padding: 100px 0px;
	}
	.form-control-inverse {
    border-color: #dcdcdc;
	}
	.form-control {
    font-size: 14px;
    border-radius: 1px;
    border-width: 1px;
	}
  </style>
</head>

<body>
  <!-- Pre-loader start -->
  <div class="theme-loader">
    <div class="ball-scale">
      <div></div>
    </div>
  </div>
  <!-- Pre-loader end -->

  <div id="pcoded" class="pcoded">
    <div class="pcoded-overlay-box"></div>
    <div class="pcoded-container navbar-wrapper">
      <!-- top menu start -->
      <jsp:include page='../include/header.jsp' />
      <!-- top menu end -->
      <!-- main container start -->
      <div class="pcoded-main-container">
        <div class="pcoded-wrapper">
          <!-- left menu start -->
          <jsp:include page='../include/side.jsp' />
          <!-- left menu end -->
          <!-- content start -->
          <div class="pcoded-content">
            <div class="pcoded-inner-content">
              <!-- main-body start -->
              <div class="main-body">
                <div class="page-wrapper">
                  <!-- page-body start -->
                  <div class="page-body">
                    <div class="row">
                      <!-- data setting start -->
                      <div class="col-md-6">
                        <c:if test="${user.user_name == 'union'}">
                        <select name="select" class="col-md-1 form-control form-control-inverse m-l-0 m-b-10 p-r-5 f-left select-left" id="selectKeyword">
                          <option>키워드</option>
                          <c:if test="${modelKeywordList == null}" >
                          	<c:forEach items="${keywordList}" var = "keywordList">
                          <option value="${keywordList.keyword_main}">${keywordList.keyword_main}</option>
                          </c:forEach>
                          </c:if>
                          <c:if test="${modelKeywordList != null}">
                          	<c:forEach items="${modelKeywordList}" var = "keywordList">
                          <option value="${keywordList.keyword_main}">${keywordList.keyword_main}</option>
                          </c:forEach>
                          </c:if>
                        </select>
						</c:if>

						<c:if test="${user.user_name != 'union'}">
                        <select name="select" class="col-md-1 form-control form-control-inverse m-b-10 m-l-0 p-r-5 f-left select-left" id="selectKeyword">
                          <option>키워드</option>
                          <c:if test="${modelKeywordList == null}" >
                          	<c:forEach items="${keywordList}" var = "keywordList">
                          <option value="${keywordList.keyword_main}">${keywordList.keyword_main}</option>
                          </c:forEach>
                          </c:if>
                          <c:if test="${modelKeywordList != null}">
                          	<c:forEach items="${modelKeywordList}" var = "keywordList">
                          <option value="${keywordList.keyword_main}">${keywordList.keyword_main}</option>
                          </c:forEach>
                          </c:if>
                        </select>
						</c:if>
                      </div>
                      <div class="col-md-6">
                        <!-- date picker start -->
                        <div class="row">
                          <div class="btn-group float-right m-b-10 p-l-15 p-r-10 col-sm-8" role="group">
                            <button id="toDay" type="button" class="btn btn-inverse btn-sm waves-effect waves-light">당일</button>
                            <button id="yesterDay" type="button" class="btn btn-inverse btn-sm waves-effect waves-light">전일</button>
                            <button id="week" type="button" class="btn btn-inverse btn-sm waves-effect waves-light">7일</button>
                            <button id="month" type="button" class="btn btn-inverse btn-sm waves-effect waves-light">30일</button>
                          </div>
                          <div class="input-group float-right date col p-l-15 m-b-10 col-sm-6">
                            <input type="text" id="fromDate" class="form-control form-control-inverse" value="" style="text-align: center;">
                            <span class="input-group-addon bg-inverse">
                              <span class="icofont icofont-ui-calendar"></span>
                            </span>
                          </div>
                        </div>
                        <!-- date picker end -->
                      </div>
                      <!-- data setting end -->
                      <div class="col-md-12">
                        <div class="conbox05">
                          <div class="col-sm-12">
                            <p class="txt_title">● TODAY 연관검색어</p>
                            <p class="txt_detail">오늘의 연관검색어 수집하여 노출합니다.</p>
                            <div class="grp_03" id="grp_03">
                              <span>
                              <c:if test="${!empty searchkeywordList}">
                              <c:forEach items="${searchkeywordList}" var = "searchkeywordList">
                              	${searchkeywordList.keyword}
                              </c:forEach>
                              </c:if>
                              <c:if test="${empty searchkeywordList}">
                              	키워드를 선택해주세요.
                              </c:if>
                              </span>
                            </div>
                          </div>
                          <br>
                          <div class="col-sm-12">
                            <p class="txt_title">● TODAY 스타리듬</p>
                            <p class="txt_detail">
                            <c:if test="${selectKey != null}">
                            	${selectKey}님의 전체적인 오늘 상황의 데이터 결과입니다.
                            </c:if>
                            <c:if test="${selectKey == null}">
                            </c:if>
                            </p>
                            <div class="grp_01">
                              <img src="../assets/images/starwatch/sub01_pop_graph_bg.png" class="grp_bg" />
                              <img src="../assets/images/starwatch/sub01_pop_graph_icon01.png" class="grp_mark" style="left:calc(${badpersen}% - 9px);" />
                              <!-- <img src="assets/images/starwatch/sub01_pop_graph_icon02.png" class="grp_stick" /> -->
                              <div class="grp_txt_left">BAD</div>
                              <div class="grp_txt_right"> GOOD</div>
                            </div>
                          </div>
                          <div class="col-sm-12">
                            <p class="txt_title">● WATCH 주간 그래프</p>
                            <p class="txt_detail">
                            <c:if test="${selectKey != null}">
                            	${selectKey}님의 주간 총 건수 추이 그래프입니다.
                            </c:if>
                            <c:if test="${selectKey == null}">
                            </c:if>
                            </p>
                            <!-- 통계보고서 그래프 start -->
                                <div class="card">
                                <div class="card-header">
                                  <h5 class="card-header-text">
                                  <font style="font-weight: bold; color: black;">
                                    <i class="icofont icofont-chart-line m-r-5"></i>검출량 그래프
                                    </font>
                                  </h5>
                                  <div class="card-header-right">
                                    <i class="icofont icofont-rounded-down"></i>
                                    <i class="icofont icofont-refresh"></i>
                                  </div>
                                </div>
                                <div class="card-block">
                                  <!-- chart start -->
                                  <div id="container" style="height:350px; width: 100%;"></div>
                                  <!-- chart end -->
                                </div>
                              </div>
                              <!-- 통계보고서 그래프 end -->
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <!-- page-body end -->
                </div>
              </div>
              <!-- main-body end -->
            </div>
          </div>
          <!-- content end -->
        </div>
      </div>
      <!-- main container end -->
    </div>
  </div>
  <!-- Warning Section Starts -->
  <!-- Older IE warning message -->
  <!--[if lt IE 9]>
    <div class="ie-warning">
      <h1>Warning!!</h1>
      <p>You are using an outdated version of Internet Explorer, please upgrade <br/>to any of the following web browsers to access this website.</p>
      <div class="iew-container">
        <ul class="iew-download">
          <li>
            <a href="http://www.google.com/chrome/">
              <img src="../assets/images/browser/chrome.png" alt="Chrome">
              <div>Chrome</div>
            </a>
          </li>
          <li>
            <a href="https://www.mozilla.org/en-US/firefox/new/">
              <img src="../assets/images/browser/firefox.png" alt="Firefox">
              <div>Firefox</div>
            </a>
          </li>
          <li>
            <a href="http://www.opera.com">
              <img src="../assets/images/browser/opera.png" alt="Opera">
              <div>Opera</div>
            </a>
          </li>
          <li>
            <a href="https://www.apple.com/safari/">
              <img src="../assets/images/browser/safari.png" alt="Safari">
              <div>Safari</div>
            </a>
          </li>
          <li>
            <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
              <img src="../assets/images/browser/ie.png" alt="">
              <div>IE (9 & above)</div>
            </a>
          </li>
        </ul>
      </div>
      <p>Sorry for the inconvenience!</p>
    </div>
  <![endif]-->
  <!-- Warning Section Ends -->

  <!-- Required Jquery -->
  <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
  <script type="text/javascript" src="../bower_components/jquery-ui/jquery-ui.min.js"></script>
  <script type="text/javascript" src="../bower_components/tether/dist/js/tether.min.js"></script>
  <script type="text/javascript" src="../bower_components/bootstrap/js/bootstrap.min.js"></script>
  <!-- jquery slimscroll js -->
  <script type="text/javascript" src="../bower_components/jquery-slimscroll/jquery.slimscroll.js"></script>
  <!-- Bootstrap date-time-picker js -->
  <script type="text/javascript" src="../assets/pages/advance-elements/moment-with-locales.min.js"></script>
  <!-- modernizr js -->
  <script type="text/javascript" src="../bower_components/modernizr/modernizr.js"></script>
  <script type="text/javascript" src="../bower_components/modernizr/feature-detects/css-scrollbars.js"></script>
  <!-- classie js -->
  <script type="text/javascript" src="../bower_components/classie/classie.js"></script>
  <!-- Morris Chart js -->
  <script src="../bower_components/raphael/raphael.min.js"></script>
  <script src="../bower_components/morris.js/morris.js"></script>
  <!-- High Chart js -->
  <script src="https://code.highcharts.com/highcharts.js"></script>
  <script src="https://code.highcharts.com/modules/series-label.js"></script>
  <script src="https://code.highcharts.com/modules/exporting.js"></script>
  <!-- echart js -->
  <script src="../assets/pages/chart/echarts/js/echarts-all.js" type="text/javascript"></script>
  <!-- Date-range picker js -->
  <script type="text/javascript" src="../bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
  <!-- i18next.min.js -->
  <script type="text/javascript" src="../bower_components/i18next/i18next.min.js"></script>
  <script type="text/javascript" src="../bower_components/i18next-xhr-backend/i18nextXHRBackend.min.js"></script>
  <script type="text/javascript" src="../bower_components/i18next-browser-languagedetector/i18nextBrowserLanguageDetector.min.js"></script>
  <script type="text/javascript" src="../bower_components/jquery-i18next/jquery-i18next.min.js"></script>
  <!-- Custom js -->
  <script type="text/javascript" src="../assets/pages/picker.js"></script>
  <script type="text/javascript" src="../assets/js/script.js"></script>
  <script src="../assets/js/pcoded.min.js"></script>
  <script src="../assets/js/demo-12.js"></script>
  <script src="../assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
  <script src="../assets/js/jquery.mousewheel.min.js"></script>
  <script src="../assets/pages/starwatch/script.js"></script>
</body>

<script type="text/javascript">

//ajax 보안
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

$(function() {
	  $(document).ajaxSend(function(e, xhr, options) {
	  	xhr.setRequestHeader(header, token);
	  });
});

$(document).ready(function(){


	  var startDateOption = decodeURI(window.location.href.split("startDate=")[1]).split("&")[0].split(" ")[0];
		var endDateOption = decodeURI(window.location.href.split("endDate=")[1]).split("&")[0].split(" ")[0];
		console.log("startDateOption: " + startDateOption);
		console.log("endDateOption: " + endDateOption);

		if(startDateOption != 'undefined' && endDateOption != 'undefined'
				&& startDateOption != '' && endDateOption != ''){
			$("#fromDate").val(startDateOption + " - " + endDateOption);
		}


		var keywordOption = decodeURI(window.location.href.split("selectKey=")[1]).split("&")[0];
		console.log("keywordOption: " + keywordOption);
		console.log(decodeURI(window.location.href.split("&selectKey=")[1]));



		var $selectKeyword = $('#selectKeyword');

		if(keywordOption != 'undefined'){
			for(var i = 0; i < $selectKeyword[0].length; i++ ){
				if($selectKeyword[0][i].value == keywordOption){
					$selectKeyword[0][i].selected = 'selected';
				}
			}
		}
		$selectKeyword[0][0].disabled = true;


		// 키워드 선택시
		$selectKeyword.change(function(){
			console.log("selectKeyword clicked....");
			console.log($('#selectKeyword option:selected').val());

			searchList();
		});
		
		// 당일 클릭시
		$('#toDay').on("click", function(){
		  console.log("toDay clicked....");
		  var date = getDate("toDay");
		  var startDate = date.startDate;
		  var endDate = date.endDate;

		  $("#fromDate").val(endDate + " - " + endDate)
		  console.log($("#fromDate").val());
		  searchList();
		});

		// 전일 클릭시
		$('#yesterDay').on("click", function(){
		  console.log("yesterDay clicked....");
		  var date = getDate("yesterDay");
		  var startDate = date.startDate;
		  var endDate = date.endDate;

		  $("#fromDate").val(startDate + " - " + endDate)
		  console.log($("#fromDate").val());
		  searchList();
		});

		// 7일  클릭시
		$('#week').on("click", function(){
		  console.log("week clicked....");
		  var date = getDate("week");
		  var startDate = date.startDate;
		  var endDate = date.endDate;

		  $("#fromDate").val(startDate + " - " + endDate)
		  console.log($("#fromDate").val());
		  searchList();
		})

		// 30일 클릭시
		$('#month').on("click", function(){
		  console.log("month clicked....");
		  var date = getDate("month");
		  var startDate = date.startDate;
		  var endDate = date.endDate;

		  $("#fromDate").val(startDate + " - " + endDate)
		  console.log($("#fromDate").val());

		  searchList();

		})

	// content 길시에 ...으로 변경
	var $content = $(".text-success");

	var size = 25;

	for (var i =1; i < $content.length; i++){
		if($content[i].innerText.length >= size){
			$content[i].textContent = $content[i].innerText.substr(0, size) + '...';
		}
	}


	//캘린더 클릭시..
	$('#fromDate').on('apply.daterangepicker', function(ev, picker) {
		   var startDate = picker.startDate.format('YYYY-MM-DD');
		   var endDate = picker.endDate.format('YYYY-MM-DD');

		   console.log("startDate: " + startDate);
		   console.log("endDate: " + endDate);

		   searchList();
	});


	$.ajax({

	      type : "POST",
		  url : "graph",
	 	  dataType : "json",
	 	  data : {success : 'success', selectKey : $("#selectKeyword option:selected").val()},
	  	  success : function(data){

	  		  console.log(data);
	  		var script = "[";


			for(var i = 0; i < data.length; i++){

				script += data[i].type1 + ",";


				if(i == data.length-1){
					script =  script.substr(0, script.length-1);
					script += "]";
				}
			}
			
			var script2 = "[";

	
			for(var i = 0; i < data.length; i++){

				script2 += '"' + data[i].writeDate + '",';

				if(i == data.length-1){
					script2 =  script2.substr(0, script2.length-1);
					script2 += "]";
	  		
				}
			}
			console.log(script);
			console.log(script2);

			// to json
			var jsonScript = JSON.parse(script);
			var jsonScript2 = JSON.parse(script2);

			areaChart(jsonScript, jsonScript2);

	  	 }
	});
	
	function areaChart(jsonScript,jsonScript2) {
		Highcharts.setOptions({
			lang: {
				thousandsSep: ','
			}
		});
		Highcharts.chart('container', {

		    title: {
		         text: ''
		    },
		    subtitle: {
		        text: ''
		    },
		    yAxis: {
		        title: {
		            text: ''
		        }
		    },
		    legend: {
		        layout: 'vertical',
		        align: 'right',
		        verticalAlign: 'middle'
		    },
		  	xAxis: {
		  	 categories: jsonScript2
		    },
		    plotOptions: {
		        series: {
		            allowPointSelect: true
		        }
		    },
		    series: [{
		        name: '검출수',
		        data: jsonScript,
		        color: '#01C0C8'
		    }],
		  	credits: {
		    	enabled : false
		    },
		  	exporting: {
	        sourceWidth: 1200,
	        sourceHeight: 330,
	        // scale: 2 (default)
	        chartOptions: {
	            subtitle: null
	        }
	    	},
		    responsive: {
		        rules: [{
		            condition: {
		                maxWidth: 500
		            },
		            chartOptions: {
		                legend: {
		                    layout: 'horizontal',
		                    align: 'center',
		                    verticalAlign: 'bottom'
		                }
		            }
		        }]
		    }
		});
		}


}); // end ready...



	

//날짜 계산 함수
function getDate(type){
	console.log("TYPE : " + type);
	var date = new Date();

 	var month = date.getMonth()+1;
 	var day = date.getDate();
 	var year = date.getFullYear();

 	var endDate = year + "-" + month + "-" + day;
 	var startDate;

 	if(type == "yesterDay"){
 		var calcDate = day-1;
 		startDate = year + "-" + month + "-" + calcDate;

 	}else if(type == "month"){
 		var calcDate = month-1;
 		
 		if(calcDate == 0){
 			calcDate = 12;
 			year -= 1;
 		}
 		
 		startDate = year + "-" + calcDate + "-" + day;

 	}else if(type == "week"){
 		var calcDate = day-7;
 		if(calcDate < 0){
 			var lastDay = (new Date(year, month-1, 0)).getDate();
 			calcDate += lastDay;
 			month -= 1;
 		}
 		startDate = year + "-" + month + "-" + calcDate;

 	}else if(type =='toDay'){
 		startDate = endDate

 	}

 	return {
 		startDate : startDate,
 		endDate : endDate
 	}
}
 	
 	function makeDateFormat(date, index){
		var splitDate = date.split(" - ")[index];
			if(splitDate != undefined){
				var returnDate = splitDate.replace("/", "-").replace("/", "-")
				return returnDate;
			}


	}
	makeDateFormat($("#fromDate").val());
 	
 	//list URL 함수
	  function searchList(event) {

	  	self.location = "statistics?"
	  				  + "&selectKey="
	  				  + $('#selectKeyword option:selected').val()
	  			      + "&startDate=" + makeDateFormat($("#fromDate").val(), 0)
		  			  + "&endDate=" +  makeDateFormat($("#fromDate").val(), 1);
	  	
	  }	 
</script>
</html>