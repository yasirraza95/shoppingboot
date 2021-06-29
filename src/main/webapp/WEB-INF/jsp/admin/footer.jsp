<script>
$(function() {
	//product validation
	$(".product").keyup(function(){
		var product = $(this).val();
		
		$.ajax({
		    url: "AdminValidation",
		    type: "post",
		    data: "name="+product,
		    success: function (response) {
				if(response == "ALREADY") {
					$('.submit').prop('disabled', true);
					$('#productValid').text("Product name already exists");
				} else if(response == "NOTHING") {
					$('.submit').prop('disabled', false);
					$('#productValid').text("");
				}
		    },
		    error: function(jqXHR, textStatus, errorThrown) {
		       console.log(textStatus, errorThrown);
		    }
		});
	});
	
	//provider validation
	$(".provider").keyup(function(){
		var provider = $(this).val();
		
		$.ajax({
		    url: "AdminValidation",
		    type: "post",
		    data: "username="+provider,
		    success: function (response) {
				if(response == "ALREADY") {
					$('.submit').prop('disabled', true);
					$('#providerValid').text("Username already exists");
				} else if(response == "NOTHING") {
					$('.submit').prop('disabled', false);
					$('#providerValid').text("");
				}
		    },
		    error: function(jqXHR, textStatus, errorThrown) {
		       console.log(textStatus, errorThrown);
		    }
		});
	});
});

</script>


<footer class="footer">
	<div
		class="d-sm-flex justify-content-center justify-content-sm-between">
		<span
			class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright
			© 2020 <a href="https://www.bootstrapdash.com/" target="_blank">Bootstrapdash</a>.
			All rights reserved.
		</span> <span
			class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted
			& made with <i class="mdi mdi-heart text-danger"></i>
		</span>
	</div>
</footer>
<!-- partial -->
</div>
</div>
</div>
<script src="${adminLink}/vendor.bundle.base.js"></script>
<script src="${adminJsLink}/off-canvas.js"></script>
<script src="${adminJsLink}/hoverable-collapse.js"></script>
<script src="${adminJsLink}/template.js"></script>
<script src="${adminJsLink}/file-upload.js"></script>
</body>
</html>
