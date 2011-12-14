$(document).ready (function() {
	displayEnergyLevelsForURL ("../rest/getEnergyLevels");
});

function showRestulness()
{
	displayRestulnessScoresForURL ("../rest/getRestfulnessScores");
	$("#restfulRow").show();
}