var badminton = function($){	
	$(function(){
		$(document).on("submit","form",function(){
			$(this).find(".btn_submit").trigger("click");
			return false;
		});
	});	
}(jQuery);