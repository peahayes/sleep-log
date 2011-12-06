/**
 * 
 */

var staffUrl;
var staffTitle;
var adminUrl;
var adminTitle;
var popupWindowStyle;
var userRole;

$(document).ready (function() {
	setupAjax();
});

function setDocVars (stUrl, stTitle, adUrl, adTitle, wStyle)
{
	staffUrl = stUrl;
	staffTitle = stTitle;
	adminUrl = adUrl;
	adminTitle = adTitle;
	popupWindowStyle = wStyle;
}

function inlineMFile()
{
	window.open ("http://www.umich.edu/~michr/Tatum_User_Guide.pdf", "Tatum_User_Guide", popupWindowStyle);
}

function inlineConfluence()
{
	if (userRole == 'ADMIN')
		window.open (adminUrl, adminTitle, popupWindowStyle);
	
	// if no role (e.g., doesn't yet have an account), show
	// staff guide
	else if (userRole == 'STAFF' || userRole == null)
		window.open (staffUrl, staffTitle, popupWindowStyle);
}

function inlineUserGuide()
{
	window.open ("http://dl.dropbox.com/u/504757/RSTL_User_Guide.pdf", "Tatum_User_Guide", popupWindowStyle);
}

function processDeleteQuestionClick (id, studyId, action)
{
	if (confirm ("Are you sure you want to delete?"))
		window.location = "../admin/" + action + "?id=" + id + "&studyId=" + studyId;
	else
		return false;
}

function processDeleteClick (id, action)
{
	if (confirm ("Are you sure you want to delete?"))
		window.location = "../admin/" + action + "?id=" + id;
	else
		return false;
}

function getRole()
{
	var ajaxParam = {
	};
	$.getJSON ("../rest/getRole", ajaxParam, function (role) {
		userRole = role;
	});
	return userRole;
}

/**
 * @function Defines the default settings for Jquery Ajax calls to be issued.
 */
function setupAjax() {
    $.ajaxSetup({
        cache : false, // required for IE to download data at each request
        async : false // Ajax call should be synchronous to decide whether to submit or not.
    });
}