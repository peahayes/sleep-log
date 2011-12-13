$(document).ready (function() 
{
	// only one of these will succeed, depending on whether we are creating 
	// a new entry or updating an existing entry
	displayEnergyLevelsForURL ("../../rest/getEnergyLevels");
	displayEnergyLevelsForURL ("../rest/getEnergyLevels");
});