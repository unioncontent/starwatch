var isMobile = {
  Android: function () {
   return navigator.userAgent.match(/Android/i);
  },
  BlackBerry: function () {
   return navigator.userAgent.match(/BlackBerry/i);
  },
  iOS: function () {
   return navigator.userAgent.match(/iPhone|iPad|iPod/i);
  },
  Opera: function () {
   return navigator.userAgent.match(/Opera Mini/i);
  },
  Windows: function () {
   return navigator.userAgent.match(/IEMobile/i);
  },
  any: function () {
   return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
  }
};
$(document).ready(function() {
  cardResize();
  // 캘린더 세팅
  $('#fromDate').daterangepicker({
    // singleDatePicker: true,
    showDropdowns: true,
    locale: {
      format: 'YYYY/MM/DD',
      "customRangeLabel": "Custom",
      "daysOfWeek": [
        "일", "월", "화", "수", "목", "금", "토"
      ],
      "monthNames": [
        "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"
      ],
    }
  });
});
// popup
$('.closeBtn').click(function() {
  $('#content , #txt_content').hide(); //팝업레이어 숨기기

  if(isMobile.any()){
    $('body').css({height: "100%",overflow: "auto",width: "100%",position: "relative"});
    $(window).scrollTop(Number(lastScroll));
  }else{
    $('body').css({overflow:'auto'});
  }
});
// popup click
$('.txtView01 img.contain_img').on('click', function(e){
  e.preventDefault();
  
  var div = event.target.parentElement.parentElement.parentElement.children;
  
  domain = div[1].innerHTML;
  
  console.log("domain: " + domain);
  
  var thumbName = div[1].children[0].value;
  console.log(thumbName);
  
  var path = 'http://overware.co.kr/classification/show?name=' + thumbName;
  console.log("path: " + path);
  $("#txt_content .conbox04 img").attr("src",path);
  $('#txt_content').show(); //팝업레이어 보이기
  //$('body').css({overflow:'hidden'});
  //$('body').css({overflow:'hidden'}).bind('touchmove',function(e){e.preventDefault();});
  if(isMobile.any()){
    $(window).off("scroll");
    lastScroll = Math.ceil($(window).scrollTop());
    $('body').css({height: "100%",overflow: "hidden",width: "100%",position: "fixed"});
  }else{
    $('body').css({overflow:'hidden'});
  }
});
// img zoom
// $('#zoomSection').imagesLoaded({ background: true }, function() {
//   // images have loaded
// }).progress( function( instance, image ) {
//   var result = image.isLoaded ? 'loaded' : 'broken';
//   console.log( 'image is ' + result + ' for ' + image.img.src );
// }).always( function( instance ) {
//   var $section = $('#zoomSection');
//   $section.find('.panzoom').panzoom({
//     $reset: $section.find(".reset"),
//     //panOnlyWhenZoomed: true,
//     increment: 2,
//     //maxScale: 0.9,
//     //disableXAxis: true,
//     minScale: 1,
//     //		                  duration: 500,
//     //contain: 'invert',
//     //contain: true
//     startTransform: 'scale(1)'
//   }).panzoom('zoom', true);
//   $('#zoomSection .reset').click();
// });

// 캘린더 클릭시
$('#fromDate').on('apply.daterangepicker', function(ev, picker) {
  var startDate = picker.startDate.format('YYYY-MM-DD');
  var endDate = picker.endDate.format('YYYY-MM-DD');
  console.log("startDate: " + startDate);
  console.log("endDate: " + endDate);
});


function cardResize(){
  setTimeout(function(){
    if($(".col-md-12 > .row").width() > 1200){
      var card = Math.ceil($(".col-md-12 > .row").width()/5)-1;
      $(".main-card").css("min-width",card);
    }
  }, 400);
}
