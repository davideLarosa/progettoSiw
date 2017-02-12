function checkPasswordMatch() {
	var password = $("#password").val();
	var confirmPassword = $("#confirm").val();

	if (password != "" && confirmPassword != "") {
		if (password.length != confirmPassword.length
				&& password != confirmPassword) {
			$("#confirm").css("border", "2px solid red");
			$("#signup_btn").attr("disabled", "disabled");
		} else {
			if (password == confirmPassword) {
				$("#confirm").css("border", "2px solid green");
				if ($("#name").val() != "" && $("#surname").val() != ""
						&& $("#email").val() != "" && $("#phone").val() != ""
						&& $("#address").val() != "") {
					$("#signup_btn").removeAttr("disabled");
				}
			}
		}
	} else {
		$("#confirm").removeAttr("style");
		$("#signup_btn").attr("disabled", "disabled");
	}
}

$(document).ready(function() {
	$("#signup_form_data").keyup(checkPasswordMatch);
});
