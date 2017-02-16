function getPreview(number) {

	function previewImage(file) {
		var galleryId = "preview" + number;

		var gallery = document.getElementById(galleryId);
		var imageType = /image.*/;

		if (!file.type.match(imageType)) {
			throw "File Type must be an image";
		}

		var thumb = document.createElement("div");
		thumb.classList.add('thumbnail');
		thumb.setAttribute("id", "thumb" + number);

		var thumbNumber = 'thumbnail' + number;

		var list = document.getElementById("thumb" + number);
		if (list != null) {
			list.parentNode.removeChild(list);
		}

		var img = document.createElement("img");
		img.file = file;
		thumb.appendChild(img);
		gallery.appendChild(thumb);

		// Using FileReader to display the image content
		var reader = new FileReader();
		reader.onload = (function(aImg) {
			return function(e) {
				aImg.src = e.target.result;
			};
		})(img);
		reader.readAsDataURL(file);
	}

	var uploadfiles = document.querySelector('#fileinput' + number);
	uploadfiles.addEventListener('change', function() {
		var files = this.files;
		previewImage(this.files[0]);

	});
};

$(document).ready(function(){
	
	for(var i = 1; i != 5 ;i++){
		getPreview(1);
	}
	
})
