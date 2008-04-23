function updateCheckboxControl(hiddenId)
{
	if('Y' == document.getElementById(hiddenId).value)
	{
		document.getElementById(hiddenId+'Control').checked = true;
	}
}

function updateCheckboxHidden(hiddenId, element)
{
	document.getElementById(hiddenId).value=(element.checked?'Y':'N')
}

/**
* Remove all the children of the specified element
* @param element The element to remove the children from
*/
function removeChildren(element)
{
	while(element.hasChildNodes())
	{
		element.removeChild(element.firstChild);
	}
}
