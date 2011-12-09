$(document).ready(function() {
	initializeAddEditPopUp();
});

/**
 * Added by Yunkyu Kwak
 * @function Using JQuery colorbox plugin converts add and edit contact links to css pop-ups
 */
function initializeAddEditPopUp() {
	//Examples of how to assign the ColorBox event to elements
	$(".editFrame").colorbox({width:"65%", height:"50%", iframe:true, scrolling: true});
	$(".example5").colorbox({width:"50%", height:"60%", inline:true, href:"#inline_example4", scrolling: false });
	$(".example6").colorbox({width:"50%", height:"75%", iframe:true, scrolling: true});
	$(".example2").colorbox({width:"50%", height:"75%", iframe:true, scrolling: true});
}