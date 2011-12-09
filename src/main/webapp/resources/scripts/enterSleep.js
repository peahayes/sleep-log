/**
 * @author: Melih Gunal 07/19/2011
 * @fileoverview Supports Add/Edit Sites. Depends on JQuery and JQuery
 *               Validation. Tested with JQuery ver 1.4.x, JQuery validation
 *               plug-in 1.8.0
 * @param cancelURL, duplicateSiteErrorMsg
 *            Global variables should be set in the page referencing this js
 *            file.
 */

var sleepForm;
var save;
var cancel;
var spnWaitImage;

//var ajaxErrBox;
//var shortName;
//var oldShortName;

/**
 * @function Sets the global variables to the most frequently used UI elements.
 */
function setGlobalElements() {
	sleepForm = $("#sleepForm");
	save = $("#save");
	cancel = $("#cancel");
	spnWaitImage = $("#spnWaitImage");
	
//	ajaxErrBox = $("#message");
//	shortName = $("#SHORTNAMe");
//	oldShortName=shortName.val();
}

/**
 * @function Sets the event handlers for the UI elements
 */
function setEventHandlers() {
	displayHideWaitImage();
	setCancelHandlers();
	setSaveHandlers();
}

/**
 * @function When cancel button is hit the user is sent back to manage studies
 *           screen.
 */
function setCancelHandlers() {
	cancel.click(function() {
		try {
			parent.$.fn.colorbox.close();
		} catch (e) {
			window.location.href = cancelURL;
		} finally {
			return false;
		}
	});
}

/**
 * @function Before the form is posted the site short name should be checked
 *           against the database to prevent duplicating site
 */
function setSaveHandlers() {
	sleepForm.submit(function() {

//		var msg = validateNoSpecialChar ($('#shortName'), ',', $('#message'), commaErrorMessage);
		
//		if(oldShortName.toLowerCase()==shortName.val().toLowerCase()) // While editing site if the site name has not been changed do not run validation for duplicates.
//			return true;
		
//		ajaxErrBox.text('');
		
//		if (siteExists (shortName.val())) {
//			ajaxErrBox.text (msg + " " + duplicateSiteErrorMsg);
//			return false;
//		}
		parent.$.fn.colorbox.close();
		return true;
	});
}

//function siteExists(name) {
//	var duplicate = false;
//	var ajaxParam = {
//		siteShortName : name
//	};
//	$.getJSON("../rest/siteExists", ajaxParam, function(exists) {
//		duplicate = exists;
//	});
//	return duplicate;
//}

$(document).ready (function() {
	setGlobalElements();
	setEventHandlers();
//	setupAjax();
//	document.forms[0].reset();
//	sleepForm.reset();

//	sleepForm.validate ({
//
//		rules : {
//			shortName : {
//				required : true,
//				minlength : 2
//			}
//		},
//
//		debug : false,
//
//		messages : {
//			shortName : "<br/>Enter a short name"
//		}
//	});
});

/**
 * @function Defines the default settings for Jquery Ajax calls to be issued.
 */
//function setupAjax() {
//	$.ajaxSetup({
//		cache : false, // required for IE to download data at each request
//		async : false, // Ajax call should be synchronous to decide whether to submit or not.
//		beforeSend : function() {
//			ajaxErrBox.html('');
//		},
//		error : function(jqXHR, textStatus, errorThrown) {
//			ajaxErrBox.html(errorThrown);
//		}
//	});
//}

/**
 * @function Whenever an Ajax call is made a wait image is displayed and hidden
 *           upon completion of the request.
 */
function displayHideWaitImage() {
	spnWaitImage.hide().ajaxStart(function() {
		save.attr('disabled', true);
		$(this).show();
	}).ajaxStop(function() {
		save.attr('disabled', false);
		$(this).hide();
	});
}