
(function ($) {
    "use strict";
    
        /*==================================================================
	[ Focus input ]*/
    $('.input100').each(function(){
        $(this).on('blur', function () {
			if($(this).val().trim() != "" || input.attr("type") == "date") {
        		$(this).addClass('has-val');
        	}
        	else {
        		$(this).removeClass('has-val');
        	}
		});

        changeInputClasses($(this));    
    })
    
    $('.input100').keypress(function(){
  		if($(this).val().trim() != "") {
        	$(this).addClass('has-val');
        }
        else {
        	$(this).removeClass('has-val');
        }
	});
  
    function changeInputClasses(input) {
				
		if(input.val().trim() != "" || input.attr("type") == "date") {
        	input.addClass('has-val');
        }
        else {
        	input.removeClass('has-val');
        }
	}
    
    var input = $('.input100');

    $('.validate-form').on('submit',function(){
        var check = true;
                  
        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }
        
        if ( $('.form-check-input').length > 0 && $('.form-check-input:checked').length < 1 ) {
			
			$('.labelcheckbox').addClass('alert-validate');
			check=false;
		}

        return check;
    });


    $('.input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });
   

    function validate (input) {
		console.log($(input).val());
        if($(input).val().trim() == '' || $(input).val() == 0){
			return false;
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();
        	
        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }

    
    $('.cgc').each(function(){
		
		var mask = "";
		
        if ( $(this).text().length > 11 ) {
			mask = "00.000.000/0000-00";
		} else {
			mask = "000.000.000-000";
		}
		
		$(this).mask(mask, {
    		onKeyPress : function(cgc, e, field, options) {
    			const masks = ['000.000.000-000', '00.000.000/0000-00'];
    			const mask = (cgc.length > 14) ? masks[1] : masks[0];
    			$('#cgc').mask(mask, options);
  			}
		});
		
    });

})(jQuery);