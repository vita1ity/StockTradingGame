
(function ($) {
$(document).ready(function(e) {
var mainConfig = new Array();



mainConfig[0] = {
	hwSlideSpeed: 400, 
	hwTimeOut: 10000, 
	hwNeedLinks: true, 
	hwNeedBullets: true,
	hwAutoRotate: true 
	}
mainConfig[1] = {
	hwSlideSpeed: 400,
	hwTimeOut: 10000,
	hwNeedLinks: true,
	hwNeedBullets: true,
	hwAutoRotate: false
	}
mainConfig[2] = {
	hwSlideSpeed: 400,
	hwTimeOut: 10000,
	hwNeedLinks: true,
	hwNeedBullets: true,
	hwAutoRotate: false
	}
mainConfig[3] = {
	hwSlideSpeed: 400,
	hwTimeOut: 10000,
	hwNeedLinks: true,
	hwNeedBullets: true,
	hwAutoRotate: false
	}
mainConfig[4] = {
	hwSlideSpeed: 400,
	hwTimeOut: 10000,
	hwNeedLinks: true,
	hwNeedBullets: true,
	hwAutoRotate: false
	}
var activeSlide = new Array();
var slideCount = new Array();
var timerid = new Array();
var neddRotatr = new Array();
	
$('.slider-wrap').each(function(index) {
	var mainThis = $(this)
	slideCount[index] = $(".slide", mainThis).size();
	activeSlide[index] = 0;
	$('.slide', mainThis).css({"position" : "absolute", "top":'0', "left": '0'}).hide().eq(0).show();
	

if(mainConfig[index].hwNeedLinks){
	var $linkArrow = '<a class="prewbutton" href="#">&lt;</a><a class="nextbutton" href="#">&gt;</a>';	 
	$(".slider", mainThis).prepend($linkArrow);
}

if(mainConfig[index].hwNeedBullets){
var $adderSpan = '';
	$('.slide', mainThis).each(function(num) {
			$adderSpan += '<span class = "control-slide">' + num + '</span>';
		});
	$(mainThis).append('<div class = "sli-links">' + $adderSpan +'</div>');
	$(".control-slide:first", mainThis).addClass("active");	
}

$('.nextbutton', mainThis).click(function(){
		animSlide(index, "next");
		return false;
		})
		
$('.prewbutton', mainThis).click(function(){
		animSlide(index, "prew");
		return false;
		})
 
$('.control-slide', mainThis).click(function(){
	var goToNum = parseFloat($(this).text());
	animSlide(index, goToNum);
	});	

if(mainConfig[index].hwAutoRotate){
mainThis.everyTime(mainConfig[index].hwTimeOut, timerid[index], function(){animSlide(index, 'next');});	
mainThis.hover(
function(){mainThis.stopTime(timerid[index]);},
function(){mainThis.everyTime(mainConfig[index].hwTimeOut, timerid[index], function(){animSlide(index, 'next');});}	
	);
}
	});

var animSlide = function(id, arrow){
	var $containerNum = $('.slider-wrap').eq(id).find(".slide");
	var activeVar = activeSlide[id];
	var countVar = slideCount[id];

if(activeVar != arrow){ 
	$containerNum.eq(activeVar).fadeOut(mainConfig[id].hwSlideSpeed);
	$('.slider-wrap').eq(id).find(".control-slide").eq(activeVar).removeClass("active");
	if(arrow == "next"){
			if(activeVar == (countVar-1)){activeVar=0;}
			else{activeVar++}
		}
	else if(arrow == "prew")
		{
			if(activeVar == 0){activeVar=countVar-1;}	
			else{activeVar-=1}
		}
	else{
			activeVar = arrow;
		}
	$containerNum.eq(activeVar).fadeIn(mainConfig[id].hwSlideSpeed);
	activeSlide[id] = activeVar;
	$('.slider-wrap').eq(id).find(".control-slide").eq(activeVar).addClass("active");
	}}


});
})(jQuery);
