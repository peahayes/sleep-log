var sleepForm;
var save;
var cancel;
var spnWaitImage;
var ajaxErrBox;

$(document).ready (function() 
{
	setGlobalElements();
	setEventHandlers();
	setupAjax();
	
	$("#sleepForm").validate({
		rules: {
			energyLevel: "required",
// don't validate these for now -- I haven't gotten the display for radio button validation working right
//			restedScore: "required",
//			restfulnessScore: "required",
			numDrinks: {
				required: true,
				minlength: 1
			}
		},
		messages: {
			energyLevel: "<span style='color:red;'>&nbsp;&nbsp;&nbsp;Please select your level of energy</span>",
//			restedScore: "Please choose how rested you feel",
//			restfulnessScore: "Please choose how restful your sleep was",
			numDrinks: {
				required: "<span style='color:red;'>&nbsp;&nbsp;&nbsp;Please enter the number of drinks you consumed</span>",
				minlength: "Number of drinks must be at least 1 digit"
			}
		}
	});

	var restfulScore = $('#savedRestfulScore');
	
	if (restfulScore != null && restfulScore.length > 0)
		showRestulness();
	else
		$("#restfulRow").hide();
	
//	document.forms[0].reset();
//	sleepForm.reset();
});


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
 * @function When cancel link is clicked, the user is sent back to main screen.
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
 * @function Before the form is posted, we do some validation.
 */
function setSaveHandlers() {
	sleepForm.submit(function() {
		parent.$.fn.colorbox.close();
		return true;
	});
}

function displayRestulnessScoresForURL (restUrl)
{
	$.getJSON (restUrl, {
		
	}, function (scores) {
		
		if (scores.length > 0) 
		{
			var restfulScore = $('input#savedRestfulScore').val();
			var buttons = '';
			
    		for (var i = 0; i < scores.length; i++)
			{
    			buttons += '<div style="height:20px; display:inline-block; width:93px;"><input name="restfulnessScore" type="radio" value="';
    			
				if (restfulScore != null && restfulScore == scores[i].value)
					buttons += scores[i].value + '" checked/>';
				else
					buttons += scores[i].value + '"/>';

				buttons += scores[i].description + '</div>';
			}
			
			$('#restfulness').html (buttons);
		}
		else
			$('#restfulness').html ("Could not retrieve restfulness scores!");
	});
}

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