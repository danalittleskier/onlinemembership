numdivs=5

IE5=NN4=NN6=false
if(document.all)IE5=true
else if(document.layers)NN4=true
else if(document.getElementById)NN6=true

function init()
{
	showDiv(0)
}
function showDiv( which )
{
	for(i=0;i<numdivs;i++)
	{
		if(NN4) eval("document.div"+i+".display='none'")
		if(IE5) eval("document.all.div"+i+".style.display='none'")
		if(NN6) eval("document.getElementById('div"+i+"').style.display='none'")
	}
	if(NN4) eval("document.div"+which+".display='block'")
	if(IE5) eval("document.all.div"+which+".style.display='block'")
	if(NN6) eval("document.getElementById('div"+which+"').style.display='block'")
}

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

/**
 * Submit a Spring MVC able form with a specified event name and value.  This is a workaround that 
 * provides a way to submit from regular input buttons using the name / value of the specified button.
 *
 * @param buttonCtl		The button whose name / value will be submitted
  */
function submitFormWithInputButton(buttonCtl)
{
	var name = $j(buttonCtl).attr('name');
	var value = $j(buttonCtl).attr('value');
	$j(buttonCtl).after("<input type='hidden' name='" + name + "' value='" + value + "'/>");
	$j(buttonCtl).parents("form").submit();
}

