var sleepForm;
var save;
var cancel;
var spnWaitImage;
var ajaxErrBox;

/**
 * @function Sets the global variables to the most frequently used UI elements.
 */
function setGlobalElements() {
	sleepForm = $("#sleepForm");
	save = $("#save");
	cancel = $("#cancel");
	spnWaitImage = $("#spnWaitImage");
	ajaxErrBox = $("#message");
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

$(document).ready (function() {
	setGlobalElements();
	setEventHandlers();
	setupAjax();
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

function displayEnergyLevelsForURL (restUrl)
{
	$.getJSON (restUrl, {
		
	}, function (levels) {
		
		if (levels.length > 0) 
		{
			var energyLevel = $('input#savedEnergyLevel').val();
			var options = '<select id="energyLevel" name="energyLevel" size="1">';
			options += '<option value="">- Choose Level -</option>';
			
			for (var i = 0; i < levels.length; i++)
			{
				if (energyLevel != null && energyLevel == levels[i].value)
					options += '<option value="' + levels[i].value + '" selected>';
				else
					options += '<option value="' + levels[i].value + '">';
				
				options += levels[i].description + '</option>';
			}
			
			options += "</select>";
			
			$('#energyLevel').html (options);
		}
		else
			$('#energyLevel').html ("Could not retrieve energy levels!");
	});
}

/**
 * @function Defines the default settings for Jquery Ajax calls to be issued.
 */
function setupAjax() {
	$.ajaxSetup({
		cache : false, // required for IE to download data at each request
		async : false, // Ajax call should be synchronous to decide whether to
						// submit or not.
		beforeSend : function() {
			ajaxErrBox.html('');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			ajaxErrBox.html(errorThrown);
		}
	});
}

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

function processDeleteClick (id, action)
{
	if (confirm ("Are you sure you want to delete?"))
		window.location = action + "?id=" + id;
	else
		return false;
}