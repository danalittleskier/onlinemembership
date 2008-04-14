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
