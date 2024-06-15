
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
	
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

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


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });
   

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
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
    
    /*==================================================================
    [ Show pass ]*/
    var showPass = 0;
    $('.btn-show-pass').on('click', function(){
        if(showPass == 0) {
            $(this).next('input').attr('type','text');
            $(this).find('i').removeClass('zmdi-eye');
            $(this).find('i').addClass('zmdi-eye-off');
            showPass = 1;
        }
        else {
            $(this).next('input').attr('type','password');
            $(this).find('i').addClass('zmdi-eye');
            $(this).find('i').removeClass('zmdi-eye-off');
            showPass = 0;
        }
        
    });
    

	$('.cgc').each(function(){
		
		var mask = "";
		
		console.log($(this).val().length);
				
        if ( $(this).val().length > 11 ) {
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