$(document).ready(function(){
	$(".toggler").click(function(){
		$(".split-pane-4").toggleClass("fourth-pane full-slide");
	});

	$("#layoutA").click(function(){
		replaceClasses("layout-a");
	});

	$("#layoutB").click(function(){
		replaceClasses("layout-b");
	});

	$("#layoutC").click(function(){
		replaceClasses("layout-c");
	});

	$("#layoutD").click(function(){
		replaceClasses("layout-d");
	});

	$("#layoutE").click(function(){
		replaceClasses("layout-e");
	});

	$("#layoutF").click(function(){
		replaceClasses("layout-f");
	});

	$("#layoutG").click(function(){
		replaceClasses("layout-g");
	});

	$(".split-pane-1").find(".panel-handle").click(function(){
		resizeColumn($(this),"layout-f","layout-a");
	});

	$(".split-pane-2").find(".panel-handle").click(function(){
		resizeColumn($(this),"layout-g","layout-a");
	});

	$(".split-pane-3").find(".panel-handle").click(function(){
		resizeColumn($(this),"layout-e","layout-a");
	});

	function resizeColumn($selector, newLayout, oldLayout){
		if($selector.hasClass("expand")){
			replaceClasses(newLayout);
			$(".page-content > div").find(".panel-handle").removeClass("retract").addClass("expand");
			$selector.removeClass("expand").addClass("retract");
		}
		else{
			replaceClasses(oldLayout);
			$selector.removeClass("retract").addClass("expand");
		}
	}

	function replaceClasses(newClassName){
		$('div[class*="layout-"]').each(function(i, el){
		    var name = (el.className.match(/(^|\s)(layout\-[^\s]*)/) || [,,''])[2];
		    if(name){
		        $(el).removeClass(name);
		    }
		});
		$(".page-content").addClass(newClassName);	
	}

	function toggleClasses(newClassName){
		var classNames = [], toggleClasses = "";
		$('div[class*="layout-"]').each(function(i, el){
		    var name = (el.className.match(/(^|\s)(layout\-[^\s]*)/) || [,,''])[2];
		    if(name){
		        classNames.push(name);
		        localStorage.setItem("previousClass",name);
		        $(el).removeClass(name);
		    }
		});
		var prevCls = localStorage.getItem("previousClass");
		if(prevCls.length > 0){
			toggleClasses += prevCls +" "+ newClassName;
		}
		else{
			toggleClasses = "layout-a";
		}
		$(".page-content").toggleClass(toggleClasses);	
	}
});