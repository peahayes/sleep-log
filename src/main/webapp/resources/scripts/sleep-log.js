/**
 * 
 */

var popupWindowStyle;

$(document).ready (function() {

});

function setDocVars (wStyle)
{
	popupWindowStyle = wStyle;
}

function processDeleteClick (id, action)
{
	if (confirm ("Are you sure you want to delete?"))
		window.location = action + "?id=" + id;
	else
		return false;
}