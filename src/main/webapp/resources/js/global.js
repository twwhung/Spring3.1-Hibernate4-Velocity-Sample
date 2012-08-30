var badminton = function($){	
	$(function(){
		$(document).on("submit","form:not(.cansubmit)",function(){
			$(this).find(".btn_submit").trigger("click");
			return false;
		});		
	});	
	return {
		_createImgTag : function(){
			var key = (new Date()).getTime()* 1000;
			return function(id){				
				key = key + 1;								
				return '<img src="./resources/images/' + id + '.jpg?'+ 
					key + '" class="player" />';
			};
		}
	};
}(jQuery);