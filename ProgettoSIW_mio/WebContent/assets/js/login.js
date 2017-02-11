function GuestData(email, password, keepSigned) {
	this.email = email;
	this.password = password;
	this.keepSigned = keepSigned;
}

function UserData(name, surname, email, phone, address, password, seller) {
	this.name = name;
	this.surname = surname;
	this.email = email;
	this.phone = phone;
	this.address = address;
	this.password = password;
	this.seller = seller;
}

function getLoginFormData() {
	var inputs = $("#login-form").find("input");
	var email = inputs.eq(0).val();
	var password = inputs.eq(1).val();
	var keepSigned = inputs.eq(2).is(":checked");
	var guest = new GuestData(email, password, keepSigned);
	login(guest);

}

function getSignupFormData() {
	var inputs = $("#signup_form_data").find("input");
	var name = inputs.eq(0).val();
	var surname = inputs.eq(1).val();
	var email = inputs.eq(2).val();
	var phone = inputs.eq(3).val();
	var address = inputs.eq(4).val();
	var password = inputs.eq(5).val();
	var confirm = inputs.eq(6).val()
	var seller = inputs.eq(7).is(":checked");

	if (name != "" && surname != "" && email != "" && phone != ""
			&& address != "" && password != "" && confirm != "") {
		if (password == confirm)
			signup(new UserData(name, surname, email, phone, address, password,
					seller));
	} else if (name == "" || surname == "" || email == "" || phone == ""
			|| address == "" || password == "" || confirm != "") {
		alert("You must compile all fields to signup");
	} else if (password == "") {
		alert("You must choose a password to signup!");
	} else if (password != confirm) {
		alert("Password doesn't match!");
	}
}
function login(guest) {
	var guestJson = {
		email : guest.email,
		password : guest.password,
		keepSigned : guest.keepSigned
	};
	$.ajax({
		type : "POST",
		url : "../LoginServlet",
		datatype : "json",
		data : {
			guest : JSON.stringify(guestJson),
		},
		success : function(data) {
			if (data == 0) {
				window.location = "../index.html";
			} else {
				var p = document.createElement("p");
				p.innerHTML = "Wrong username or password"
				$("#error_message").each(function() {
					if (!$(this).find("p").length > 0) {
						$("#error_message").append(p);
					}
				});
			}
		}
	});
}

function signup(user) {
	var userJson = {
		name : user.name,
		surname : user.surname,
		email : user.email,
		phone : user.phone,
		address : user.address,
		password : user.password,
		seller : user.seller
	};
	$.ajax({
		type : "POST",
		url : "../SignupServlet",
		datatype : "json",
		data : {
			user : JSON.stringify(userJson),
		},
		success : function(data) {
			if (data == 0) {
				alert("Registration Succesfull!");
			} else if (data == 1) {
				alert("User already exists!");
			} else if (data == 2) {
				alert("Some error occurred");
			}
		}
	});

}

function init() {
	var form_login_button = $("#form-login-button");
	form_login_button.on('click', getLoginFormData);

	var form_signup_button = $("#form_signup_button");
	form_signup_button.on('click', getSignupFormData);
}

//$(document).ready(init);