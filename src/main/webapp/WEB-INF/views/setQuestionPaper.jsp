<head>
<script src="js/ckeditor.js"></script>
<script src="js/sample.js"></script>
<script src="js/setQuestionPaper.js"></script>
<!-- 	<link rel="stylesheet" href="css/samples.css"> -->
<link rel="stylesheet" href="css/neo.css">
</head>

<div class="ct-site--map ct-u-backgroundGradient">
	<div class="container">
		<div class="ct-u-displayTableVertical text-capitalize">
			<div class="ct-u-displayTableCell">
				<span class="ct-u-textNormal">Question Paper Settings</span>
			</div>
			<div class="ct-u-displayTableCell text-right">
				<span class="ct-u-textNormal ct-u-textItalic"> <a
					href="index.html">Settings</a> / Set Question Paper<a href="#"></a>
				</span>
			</div>
		</div>
	</div>
</div>
<br>
<div style="padding: 15px;  line-height:2.428571;">
	<div class="col-xs-4 form-group">
		<label for="board">Board:</label> 
		<select class="form-control" id="board">
		</select>
	</div>
	<div class="col-xs-4 form-group">
		<label for="standard">Standard:</label> 
		<select class="form-control" id="standard">
		</select>
	</div>
	<div class="col-xs-4 form-group">
		<label for="sel1">Subject:</label> 
		<select class="form-control" id="sel1">
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
		</select>
	</div>

<div class="adjoined-bottom" style="height: 402px;">
	<div class="grid-container">
		<div class="grid-width-100">
			<div id="editor">
				<h1>Hello world!</h1>
				<p>
					I'm an instance of <a href="http://ckeditor.com">CKEditor</a>.
				</p>
			</div>
		</div>
	</div>
</div>
	<button type="button" class="btn btn-primary">Create Paper</button>
	<button type="button" class="btn btn-seconday">Cancel</button>
</div>
<br>
<script>
	initSample();
</script>

</body>
</html>
