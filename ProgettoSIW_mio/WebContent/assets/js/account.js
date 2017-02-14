function User(name, surname, email, phone, address, password, confirm) {
	this.name = name;
	this.surname = surname;
	this.email = email;
	this.phone = phone;
	this.address = address;
	this.password = password;
	this.confirm = confirm;
}

function checkPasswordMatch() {
	var password = $("#password").val();
	var confirmPassword = $("#confirm").val();

	if (password != "" && confirmPassword != "") {
		if (password.length != confirmPassword.length
				&& password != confirmPassword) {
			$("#confirm").css("border", "2px solid red");
			$("#save_btn").attr("disabled", "disabled");
		} else {
			if (password == confirmPassword) {
				$("#confirm").css("border", "2px solid green");
				if ($("#name").val() != "" && $("#surname").val() != ""
						&& $("#email").val() != "" && $("#phone").val() != ""
						&& $("#address").val() != "") {
					$("#save_btn").removeAttr("disabled");
				}
			}
		}
	} else {
		$("#confirm").removeAttr("style");
		$("#save_btn").attr("disabled", "disabled");
	}
}

function sendData() {
	var name = $("#name").val();
	var surname = $("#surname").val();
	var email = $("#email").val();
	var phone = $("#phone").val();
	var address = $("#address").val();
	var password = $("#password").val();
	var confirm = $("#confirm").val();

	var user = new User(name, surname, email, phone, address, password, confirm);

	var jsonUser = {
		name : user.name,
		surname : user.surname,
		email : user.email,
		phone : user.phone,
		address : user.address,
		password : user.password,
		confirm : user.confirm,
	}
	$.ajax({
		type: "POST",
		url: "modify",
		datatype: "json",
		data: {
			toModify: JSON.stringify(jsonUser),
		},
		success: function(data){
			location.reload();
		},
		error: function(data){
			alert("Sorry some error occured please retry later");	
		}
	});
}

function init() {
	$("#modify_form_data").submit(function(event){
		event.preventDefault();
	});
	$("#save_btn").on("click", function() {
		sendData();
	});
}

$(document).ready(function() {
	$("#modify_form_data").keyup(checkPasswordMatch);
});

$(document).ready(init);