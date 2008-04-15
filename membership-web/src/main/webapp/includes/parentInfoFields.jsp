<%@ include file="/common/taglibs.jsp"%>

<fieldset>
	<legend>Parent/Guardian #1</legend>
	<label for="">* First Name:</label>
	<form:input path="member.parentInfo.parent1First" size="30" maxlength="30"/><br />
	<label for="">* Last Name:</label>
	<form:input path="member.parentInfo.parent1Last" size="30" maxlength="30"/><br />
	<label for="">* Relationship:</label>
	<div class="radios">
	<form:radiobutton id="relationship-1-1" path="member.parentInfo.parent1Relation" value="Mother"/> <label class="radio" for="relationship-1-1">Mother</label>
	<form:radiobutton id="relationship-1-2" path="member.parentInfo.parent1Relation" value="Father"/> <label class="radio" for="relationship-1-2">Father</label>
	<form:radiobutton id="relationship-1-3" path="member.parentInfo.parent1Relation" value="Other"/> <label class="radio" for="relationship-1-3">Other</label>
	</div><br />
	<label for="">* Email Address:</label>
	<form:input path="member.parentInfo.parent1Email" size="30" maxlength="60"/><br />
</fieldset>
<fieldset>
	<legend>Parent/Guardian #2</legend>
	<label for="">First Name:</label>
	<form:input path="member.parentInfo.parent2First" size="30" maxlength="30"/><br />
	<label for="">Last Name:</label>
	<form:input path="member.parentInfo.parent2Last" size="30" maxlength="30"/><br />
	<label for="">Relationship:</label>
	<div class="radios">
	<form:radiobutton id="relationship-2-1" path="member.parentInfo.parent2Relation" value="Mother"/> <label class="radio" for="relationship-2-1">Mother</label>
	<form:radiobutton id="relationship-2-2" path="member.parentInfo.parent2Relation" value="Father"/> <label class="radio" for="relationship-2-2">Father</label>
	<form:radiobutton id="relationship-2-3" path="member.parentInfo.parent2Relation" value="Other"/> <label class="radio" for="relationship-2-3">Other</label>
	</div><br />
	<label for="">Email Address:</label>
	<form:input path="member.parentInfo.parent2Email" size="30" maxlength="60"/><br />
</fieldset>
