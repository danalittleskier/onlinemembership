function gettrailobj(){
if (document.getElementById)
return document.getElementById("preview_div").style
}

function gettrailobjnostyle(){
if (document.getElementById)
return document.getElementById("preview_div")
}

function hidetrail(){	
	gettrailobj().display= "none";
	//document.onmousemove=""
	//gettrailobj().left="-500px"
	//clearTimeout(timer);
}

function getQuickLinks(width,height){

         html = '<div style="width:'+  width +'px;height:'+ height +'px"><h2><a onclick="hidetrail();"><b>X CLOSE</b></a></h2><br/>';
    
        html = athleteRanking(html);
        html = eventsLookup(html);
        html = athleteHistory(html);
        html = html + '<a href="#">Staff Directory</a><br/><br/>';
        html = html + '<a href="#">MemberList</a><br/><br/>';
        html = pointsLists(html);
        html = formsPubs(html);
        html = html + '<a href="#">Software</a><br/><br/>';
        html = html + '<a href="#">Become a Member</a><br/><br/>';
        html = html + '<a href="#">USSA and FIS Applications</a><br/><br/>';
        html = html + '<a href="#">Member FAQs</a><br/><br/>';
        html = html + '<a href="#">Background Screening</a><br/><br/>';
        html = html + '<a href="#">Member Lookup</a><br/><br/>';
        html = html + '</div>';
    
        if(navigator.userAgent.indexOf("MSIE")!=-1 && navigator.userAgent.indexOf("Opera")==-1 ){
			html = html +'<iframe src="about:blank" scrolling="no" frameborder="0" width="'+width+'" height="'+height+'"></iframe>';
	}		

	gettrailobjnostyle().innerHTML = html;
	gettrailobj().display='';
    
}


function athleteRanking(html){
	        alink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=ALP"';
                clink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=XC"';
                flink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=FRE"';
                jlink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=ALP"';
                slink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=BRD"';
		
                html = html + 
                    '<div>Athlete Ranking&nbsp;&nbsp;<select style="width:100px;">' + 
                    '<option onclick=window.location='+alink+';>alpine' +
                    '<option onclick=window.location='+flink+';>freestyle' +
                    '<option onclick=window.location='+clink+';>xc' +
                    '<option onclick=window.location='+jlink+';>jump/nc' +
                    '<option onclick=window.location='+slink+';>snowboarding' +
                    '<option onclick=window.open('+alink+');>other' +
                '</select></div>';
	
        return html;
}

function eventsLookup(html){
	        alink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=ALP"';
                clink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=XC"';
                flink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=FRE"';
                jlink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=ALP"';
                slink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=BRD"';
		
                html = html + 
                    '<div>Events Lookup&nbsp;&nbsp;<select style="width:100px;">' + 
                    '<option onclick=window.location='+alink+';>FIS' +
                    '<option onclick=window.location='+flink+';>USSA' +
                '</select></div>';
	
        return html;
}

function athleteHistory(html){
	        alink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=ALP"';
                clink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=XC"';
                flink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=FRE"';
                jlink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=ALP"';
                slink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=BRD"';
		
                html = html + 
                    '<div>Athlete History&nbsp;&nbsp;<select style="width:100px;">' + 
                    '<option onclick=window.location='+alink+';>FIS' +
                    '<option onclick=window.location='+flink+';>Alpine' +
                    '<option onclick=window.location='+clink+';>Freestyle' +
                    '<option onclick=window.location='+jlink+';>XC' +
                    '<option onclick=window.location='+slink+';>Jump/NC' +
                    '<option onclick=window.open('+alink+');>Snowboarding' +
                '</select></div>';
	
        return html;
}

function pointsLists(html){
	        alink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=ALP"';
                clink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=XC"';
                flink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=FRE"';
                jlink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=ALP"';
                slink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=BRD"';
		
                html = html + 
                    '<div>Athlete Ranking&nbsp;&nbsp;<select style="width:100px;">' + 
                    '<option onclick=window.location='+alink+';>Alpine' +
                    '<option onclick=window.location='+flink+';>Freestyle' +
                    '<option onclick=window.location='+clink+';>XC' +
                    '<option onclick=window.location='+slink+';>Snowboarding' +
                '</select></div>';
	
        return html;
}

function formsPubs(html){
	        alink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=ALP"';
                clink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=XC"';
                flink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=FRE"';
                jlink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=ALP"';
                slink = '"http://services.ussa.org/USSATools/public/compservices/PointsList.jsp?SportCode=BRD"';
		
                html = html + 
                    '<div>Athlete Ranking&nbsp;&nbsp;<select style="width:100px;">' + 
                    '<option onclick=window.location='+alink+';>General' +
                    '<option onclick=window.location='+flink+';>Alpine' +
                    '<option onclick=window.location='+clink+';>Alp Masters' +
                    '<option onclick=window.location='+jlink+';>Freestyle' +
                    '<option onclick=window.location='+slink+';>Jump/NC' +
                    '<option onclick=window.open('+alink+');>Snowboarding' +
                '</select></div>';
	
        return html;
}
