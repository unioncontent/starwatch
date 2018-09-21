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
  
  <!-- 레이어 팝업 -->
  <div id="txt_content" class="hide">
    <div class="conWrap">
      <span class="closeBtn"><img src="../assets/images/starwatch/close_btn.png" alt="닫기" width="30"></span>
      <div class="conbox04">
        <section id="zoomSection">
          <div class="panzoom-parent">
            <img src="../assets/images/starwatch/test.jpg" class="panzoom">
          </div>
          <button class="reset" style="display:none;">Reset</button>
        </section>
      </div>
    </div>
  </div>
  <!-- 레이어 팝업 -->
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
                        <select id = "selectPart" name="select" class="col-md-1 form-control form-control-inverse m-b-10 p-r-5 f-left select-left">
                          <option>분류</option>
                          <option value="media">뉴스</option>
                          <option value="write">게시글</option>
                          <option value="reply">댓글</option>
                        </select>
                        <select id = "selectTextType" name="select" class="col-md-1 form-control form-control-inverse m-b-10 p-r-5 f-left select-left">
                          <option>분류글</option>
                          <option value="좋은글">좋은글</option>
                          <option value="나쁜글">나쁜글</option>
                          <option value="관심글">관심글</option>
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
                        <select id = "selectPart" name="select" class="col-md-1 form-control form-control-inverse m-b-10 p-r-5 f-left select-left">
                          <option>분류</option>
                          <option value="media">뉴스</option>
                          <option value="write">게시글</option>
                          <option value="reply">댓글</option>
                        </select>
                        <select id = "selectTextType" name="select" class="col-md-1 form-control form-control-inverse m-b-10 p-r-5 f-left select-left">
                          <option>분류글</option>
                          <option value="좋은글">좋은글</option>
                          <option value="나쁜글">나쁜글</option>
                          <option value="관심글">관심글</option>
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
                        <ul id="boardList">
                          <li class="sub02_tit">
                          <c:if test="${ '좋은글' eq textType && 'media' eq part}">
                          	<img src="../assets/images/starwatch/sub02_tit.png" alt="뉴스_호평기사">
                          	<span class="sub02_txt02"><img src="../assets/images/starwatch/sub02_img01.png" alt="뉴스_호평기사"> 호평기사 : ${total}건</span>
                          </c:if>
                          <c:if test="${ '나쁜글' eq textType && 'media' eq part}">
                          	<img src="../assets/images/starwatch/sub02_tit.png" alt="뉴스_악평기사">
                          	<span class="sub02_txt02"><img src="../assets/images/starwatch/sub01_img02.png" alt="뉴스_호평기사"> 악평기사 : ${total}건</span>
                          </c:if>
                          <c:if test="${ '관심글' eq textType && 'media' eq part}">
                          	<img src="../assets/images/starwatch/sub02_tit.png" alt="뉴스_관심기사">
                          	<span class="sub02_txt02"><img src="../assets/images/starwatch/sub01_img03.png" alt="뉴스_호평기사"> 관심기사 : ${total}건</span>
                          </c:if>
                          
                          <c:if test="${ '좋은글' eq textType && 'write' eq part}">
                          	<img src="../assets/images/starwatch/sub01_tit02.png" alt="뉴스_호평기사">
                          	<span class="sub02_txt02"><img src="../assets/images/starwatch/sub02_img02.png" alt="게시_긍정글"> 긍정 글 : ${total}건</span>
                          </c:if>
                          <c:if test="${ '나쁜글' eq textType && 'write' eq part}">
                          	<img src="../assets/images/starwatch/sub01_tit02.png" alt="뉴스_악평기사">
                          	<span class="sub02_txt02"><img src="../assets/images/starwatch/sub02_img04.png" alt="게시_비방글"> 비방 글 : ${total}건</span>
                          </c:if>
                          <c:if test="${ '관심글' eq textType && 'write' eq part}">
                          	<img src="../assets/images/starwatch/sub01_tit02.png" alt="뉴스_관심기사">
                          	<span class="sub02_txt02"><img src="../assets/images/starwatch/sub02_img05.png" alt="게시_관심글"> 관심 글 : ${total}건</span>
                          </c:if>
                          
                          <c:if test="${ '좋은글' eq textType && 'reply' eq part}">
                          	<img src="../assets/images/starwatch/sub01_tit03.png" alt="뉴스_호평기사">
                          	<span class="sub02_txt02"><img src="../assets/images/starwatch/sub02_img03.png" alt="긍정 댓글"> 긍정 댓글 : ${total}건</span>
                          </c:if>
                          <c:if test="${ '나쁜글' eq textType && 'reply' eq part}">
                          	<img src="../assets/images/starwatch/sub01_tit03.png" alt="뉴스_악평기사">
                          	<span class="sub02_txt02"><img src="../assets/images/starwatch/sub02_img04.png" alt="악성 댓글"> 악성 댓글 : ${total}건</span>
                          </c:if>
                          <c:if test="${ '관심글' eq textType && 'reply' eq part}">
                          	<img src="../assets/images/starwatch/sub01_tit03.png" alt="뉴스_관심기사">
                          	<span class="sub02_txt02"><img src="../assets/images/starwatch/sub02_img05.png" alt="긍정 댓글"> 관심 댓글 : ${total}건</span>
                          </c:if>
                          </li>
                          <c:if test="${ 'media' eq part}">

                          <c:if test="${empty detailMediaList}">
                          <div class="listWrap">
                            <ul style="margin-top: 50px;">
                            <li style="vertical-align:middle; text-align: center;">
                            	<b>등록된 기사글이 없습니다.</b>
                            </li>
                            </ul>
                          </div>
                          </c:if>
                          
                      	  <c:forEach items="${detailMediaList}" var = "detailM" varStatus="index">
                          <div class="listWrap">
                            <ul class="list">
                            <input type='hidden' value='${part}' name='part'>
                            <input type='hidden' value='${textType}' name='textType'>
                              <li class="no sts_cnt">${totalCount -index.count +1 -minusCount}.</li>
                              <li>
                                <ul class="list_txtView">
                                  <li class="txtView01">
                                  	<c:if test="${ '나쁜글' eq textType}">
                                  		<img class="contain_img" src="../assets/images/starwatch/sub_icon01.png">
                                  	</c:if>
                                    <p><a href="${detailM.url}" target="_blank">
                                    <div class='content-nowrap2'>${detailM.media_title}</div>
                                    </a></p>
                                  </li>
                                  <li class="txtView02"><img src="../assets/images/starwatch/sub02_icon01.png" alt="사이트">${detailM.media_name}</li><br>
                                  <li class="txtView02"><img src="../assets/images/starwatch/sub02_icon02.png" alt="기자명">${detailM.reporter_name}</li>
                                </ul>
                                <div><input type='hidden' value='${detailM.thumbnail}' name='thumbnail'></div>
                                <div class="day">${detailM.writeDate}</div>
                              </li>
                            </ul>
                          </div>
                        </ul>
                        </c:forEach>
                        </c:if>
                        <c:if test="${ 'write' eq part}">
                        
                        <c:if test="${empty detailWriteList}">
                          <div class="listWrap">
                            <ul style="margin-top: 50px;">
                            <li style="vertical-align:middle; text-align: center;">
                            	<b>등록된 게시글이 없습니다.</b>
                            </li>
                            </ul>
                          </div>
                          </c:if>
                        
                      	  <c:forEach items="${detailWriteList}" var = "detailM" varStatus="index">
                          <div class="listWrap">
                            <ul class="list">
                            <input type='hidden' value='${part}' name='part'>
                            <input type='hidden' value='${textType}' name='textType'>
                              <li class="no sts_cnt">${totalCount -index.count +1 -minusCount}.</li>
                              <li>
                                <ul class="list_txtView">
                                  <li class="txtView01">
                                  	<c:if test="${ '나쁜글' eq textType}">
                                  		<img class="contain_img" src="../assets/images/starwatch/sub_icon01.png">
                                  	</c:if>
                                    <p><a href="${detailM.url}" target="_blank">
                                    <div class='content-nowrap2'>${detailM.community_title}</div>
                                    </a></p>
                                  </li>
                                  <li class="txtView02"><img src="../assets/images/starwatch/sub02_icon01.png" alt="사이트">${detailM.community_name}</li>
                                  <%-- <br><li class="txtView02"><img src="../assets/images/starwatch/sub02_icon03.png" alt="작성자">${detailM.community_writer}</li> --%>
                                </ul>
                                <div><input type='hidden' value='${detailM.thumbnail}' name='thumbnail'></div>
                                <div class="day">${detailM.writeDate}</div>
                              </li>
                            </ul>
                          </div>
                        </ul>
                        </c:forEach>
                        </c:if>
                        <c:if test="${ 'reply' eq part}">
                        
                          <c:if test="${empty detailReplyList}">
	                          <div class="listWrap">
	                            <ul style="margin-top: 50px;">
	                            <li style="vertical-align:middle; text-align: center;">
	                            	<b>등록된 댓글이 없습니다.</b>
	                            </li>
	                            </ul>
	                          </div>
                          </c:if>
                        
                      	  <c:forEach items="${detailReplyList}" var = "detailM" varStatus="index">
                          <div class="listWrap">
                            <ul class="list">
                            <input type='hidden' value='${part}' name='part'>
                            <input type='hidden' value='${textType}' name='textType'>
                              <li class="no sts_cnt">${totalCount -index.count +1 -minusCount}.</li>
                              <li>
                                <ul class="list_txtView">
                                  <li class="txtView01">
                                  	<%-- <c:if test="${ '나쁜글' eq textType}">
                                  		<img class="contain_img" src="../assets/images/starwatch/sub_icon01.png">
                                  	</c:if> --%>
                                    <p><a href="${detailM.url}" target="_blank">
                                    <div class='content-nowrap2'>${detailM.reply_content}</div>
                                    </a></p>
                                  </li>
                                  <li class="txtView02"><img src="../assets/images/starwatch/sub02_icon01.png" alt="사이트">naver</li>
                                  <br><li class="txtView02"><img src="../assets/images/starwatch/sub02_icon03.png" alt="작성자">${detailM.reply_writer}</li>
                                </ul>
                                <%-- <div><input type='hidden' value='${detailM.thumbnail}' name='thumbnail'></div> --%>
                                <div class="day">${detailM.writeDate}</div>
                              </li>
                            </ul>
                          </div>
                        </ul>
                        </c:forEach>
                        </c:if>
                        <div class="m-t-5">
                          <ul class="pagination float-right">
						  <c:if test="${pageMaker.prev}">
							<li class="page-item">
							<c:if test="${ 'media' eq part}">
							<a class="page-link" href="detail${pageMaker.makeSearch(pageMaker.startPage - 1) }&part=media" aria-label="Previous">&laquo;
							</c:if>
							<c:if test="${ 'write' eq part}">
							<a class="page-link" href="detail${pageMaker.makeSearch(pageMaker.startPage - 1) }&part=write" aria-label="Previous">&laquo;
							</c:if>
							<c:if test="${ 'reply' eq part}">
							<a class="page-link" href="detail${pageMaker.makeSearch(pageMaker.startPage - 1) }&part=reply" aria-label="Previous">&laquo;
							</c:if>
							<span aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
							</a>
							</li>
						  </c:if>
						  <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
							<li class= "${pageMaker.cri.page == idx? 'active':''} page-item">
							<c:if test="${ 'media' eq part}">
							<a class="page-link" href="detail${pageMaker.makeSearch(idx)}&part=media">${idx}</a>
							</c:if>
							<c:if test="${ 'write' eq part}">
							<a class="page-link" href="detail${pageMaker.makeSearch(idx)}&part=write">${idx}</a>
							</c:if>
							<c:if test="${ 'reply' eq part}">
							<a class="page-link" href="detail${pageMaker.makeSearch(idx)}&part=reply">${idx}</a>
							</c:if>
							</li>
						  </c:forEach>
						  <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
							<li class="page-item">
							<c:if test="${ 'media' eq part}">
							<a class="page-link" href="detail${pageMaker.makeSearch(pageMaker.endPage +1)}&part=media" aria-label="Next">&raquo;
							</c:if>
							<c:if test="${ 'write' eq part}">
							<a class="page-link" href="detail${pageMaker.makeSearch(pageMaker.endPage +1)}&part=write" aria-label="Next">&raquo;
							</c:if>
							<c:if test="${ 'reply' eq part}">
							<a class="page-link" href="detail${pageMaker.makeSearch(pageMaker.endPage +1)}&part=reply" aria-label="Next">&raquo;
							</c:if>
							<span aria-hidden="true"></span>
							<span class="sr-only">Next</span>
							</a>
							</li>
						  </c:if>
						  </ul>
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
	
	var textOption = decodeURI(window.location.href.split("textType=")[1]).split("&")[0];
	console.log("textOption: " + textOption);

	var $selectTextType = $('#selectTextType');

	if(selectTextType != 'undefined'){
		for(var i = 0; i < $selectTextType[0].length; i++ ){
			if($selectTextType[0][i].value == textOption){
				$selectTextType[0][i].selected = 'selected';
			}
		}
	}
	$selectTextType[0][0].disabled = true;


	// 분류 선택시
	$selectTextType.change(function(){
		console.log("selectTextType clicked....");
		console.log($('#selectTextType option:selected').val());

		searchList();
	});
	
	var partOption = decodeURI(window.location.href.split("part=")[1]).split("&")[0];
	console.log("partOption: " + partOption);

	var $selectPart = $('#selectPart');

	if(selectPart != 'undefined'){
		for(var i = 0; i < $selectPart[0].length; i++ ){
			if($selectPart[0][i].value == partOption){
				$selectPart[0][i].selected = 'selected';
			}
		}
	}
	$selectPart[0][0].disabled = true;


	// 파트 선택시
	$selectPart.change(function(){
		console.log("selectPart clicked....");
		console.log($('#selectPart option:selected').val());

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


// 검색버튼 클릭시
$('#searchBtn').on("click", function(event){
  console.log("searchBtn clicked....");
  console.log($('#selectSearchType option:selected').val());

  if($('#keywordInput').val() == ''){
	alert("검색어를 입력해주세요.");
  }else{
	searchList();
  }
});


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

	  	self.location = "detail?"
	  				  + "&selectKey="
	  				  + $('#selectKeyword option:selected').val()
	  				  + "&part="
	  				  + $("#selectPart option:selected").val()
	  				  + "&textType=" + $("#selectTextType option:selected").val()
	  			      + "&startDate=" + makeDateFormat($("#fromDate").val(), 0)
		  			  + "&endDate=" +  makeDateFormat($("#fromDate").val(), 1);
	  	
	  }	 
</script>
</html>