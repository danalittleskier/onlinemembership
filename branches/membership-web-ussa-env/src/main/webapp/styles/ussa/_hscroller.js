/*
*	Author: @Thomas Ene
*
*	Audit:	
*	07/31/2007, v0.0.1
*		- First Draft
*
*
*	Some comments:
*		- For the "overflow:hidden" to work in IE6~7 the height or width
*		MUST be specified ! ( ex: 100% will work )
*		
*		- You could consider using "white-space:no-wrap" in the DATA div !
*
*		- IE6 hates Document Strict types ( preventing overflow:hidden ) to
*		work correctly	
*
*	Tested with: FF2, Opera9.2, IE6
*/


/* Constructor */
	function hscroller(container_id, data_id)
	{
		// Init
			this.running = 0;
			// Container
			this.id_container = container_id;
			this.s_container = null;
			// Data
			this.id_data = data_id;
			this.s_data = null;
			
			this.x_pos = 0;
			
		// Default variables
			this.direction = 0; /* 0->left, 1->right */
			this.timeout_speed = 30;
			this.scroll_speed = 1;
		
		// Constructor
			var tobj;
			
			tobj = document.getElementById(container_id);
			if (tobj!=null) this.s_container = tobj;
			
			tobj = document.getElementById(data_id);
			if (tobj!=null) this.s_data = tobj;
			
			if (this.s_container==null || this.s_data==null)
				{ alert("Cannot find ["+container_id+"] or ["+data_id+"]!"); return; }
			
			// Assure some sanity check
				this.s_container.style.overflow = 'hidden';
				this.s_data.style.position = 'relative';
				this.s_data.style.display = 'inline';
				
				this.resume();
				
	}

// Pause
	hscroller.prototype.pause = function()
	{
		this.running = 0;
	}
	
// Resume
	hscroller.prototype.resume = function()
	{
		this.running = 1;
	}
	
// Change direction
	hscroller.prototype.switch_direction = function()
	{
		this.direction = this.direction==0 ? 1 : 0;
	}
	
// Roll
	hscroller.prototype.roll = function()
	{
		if (this.running == 0) return;
		
		// Left
		if (this.direction == 0)
		{
			if (this.x_pos + this.s_data.offsetWidth < 0)
				this.x_pos = this.s_container.offsetWidth;
			this.x_pos -= this.scroll_speed;
		}
		else
		{
			if (this.x_pos > this.s_container.offsetWidth)
				this.x_pos = -this.s_data.offsetWidth;
			this.x_pos += this.scroll_speed;
		}
		
		// Perform
		this.s_data.style.left = this.x_pos+'px';
	}
	
// Start
	hscroller.prototype.start = function()
	{
		// Reset the position anyway
		this.x_pos = this.direction == 0 ? this.s_container.offsetWidth : -this.s_data.offsetWidth;
		
		if (typeof(eval(this.timer)) != "undefined")
		{
			alert("hscroller->start: Object is already running "+
				"("+this.id_container+","+this.id_data+") !");
			return;
		}
		
		var _this = this;
		
		this.timer = window.setInterval(	function(){ _this.roll(); },
										_this.timeout_speed);
		
		this.s_container.onmouseover = function() { _this.pause(); }
		this.s_container.onmouseout = function() { _this.resume(); }
	}
