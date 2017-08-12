var editor; // use a global for the submit and return data rendering in the
// examples
var table;
var boardsDropDownJSON = null;
var classLevelDropDownJSON = null;
var classLevelTypeDropDownJSON = null;
var nextIdToGenerate = 0;
$(document).ready(
		function() {
			var token = $("meta[name='_csrf']").attr("content");
			$.ajaxSetup({
				beforeSend : function(xhr) {
					xhr.setRequestHeader('X-CSRF-TOKEN', token);
				}
			});
			
			$.ajax({
				type : 'POST',
				url : 'getBoards.do',
				contentType : 'application/json',
				dataType : 'json',
				data : {
					'csrfmiddlewaretoken' : $("meta[name='_csrf']").attr(
							"content")
				},
				error : function() {
					$('#info').html('<p>An error has occurred</p>');
				},
				success : function(data) {
					boardsDropDownJSON = $.parseJSON(JSON.stringify(data));
					console.log(boardsDropDownJSON);
					getClassLevelDropDown();
				},
			});
			function getClassLevelDropDown() {
				$.ajax({
					url : 'getClassLevel.do',
					contentType : 'application/json',
					dataType : 'json',
					data : {
						'csrfmiddlewaretoken' : $("meta[name='_csrf']").attr(
								"content")
					},
					error : function() {
						$('#info').html('<p>An error has occurred</p>');
					},
					success : function(data) {
						classLevelDropDownJSON = $.parseJSON(JSON.stringify(data));
						console.log(classLevelDropDownJSON);
						getClassNameDropDown();
					},
					type : 'POST'
				});
			}
			
			function getClassNameDropDown() {
				$.ajax({
					type : 'POST',
					url : 'getClassNameList.do',
					contentType : 'application/json',
					dataType : 'json',
					data : {
						'csrfmiddlewaretoken' : $("meta[name='_csrf']").attr(
								"content")
					},
					error : function() {
						$('#info').html('<p>An error has occurred</p>');
					},
					success : function(data) {
						classLevelTypeDropDownJSON = $.parseJSON(JSON.stringify(data));
						console.log(classLevelTypeDropDownJSON);
							formTableNEditor();
					}
				});
			}

			function formTableNEditor() {
				
				editor = new $.fn.dataTable.Editor({
					ajax : {
						type : 'POST',
						contentType : 'application/json',
						data : function(d) {
							return JSON.stringify(d);
						},
						url : 'subjectAction.do',
						submitSuccess : function(e, json, data) {
							debugger;
							alert(json);
							table.ajax.reload();
						},

						submitError : function(xhr, error, thrown) {
							console.log(xhr, error, thrown);
						}

					},
					idSrc : 'subjectId',
					table : "#subjectTable",
					fields : [ {
						label : "Subject Id",
						name : "subjectId"
					},{
						label : "Subject Name:",
						name : "subjectName"
					},{
						label : "Board Name:",
						name : "classLevelType.board.boardId",
						type : "select",
						options : boardsDropDownJSON
					},{
						label : "Class Level:",
						name : "classLevelType.classLevel.classLevelId",
						type : "select",
						options : classLevelDropDownJSON
					},
					{
						label : "Class Name:",
						name : "classLevelType.className",
						type : "select",
						options : classLevelTypeDropDownJSON
					},{
						label : "Language:",
						name : "classLevelType.language",
						type : "select",
						options : [{"label":"Hindi", "value" : "Hindi"},
						           {"label": "English", "value":"English"}]
					} ],
					submitSuccess : function(e, json, data) {
						debugger;
						alert(json);
						table.ajax.reload();
					},
				});
				
				var dataToSend = {
					action : "getAll"
				};
				table = $('#subjectTable').DataTable(
						{
							lengthChange : true,
							paging		 : false,
							/*"language": {
					            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Hindi.json"
					        },*/
							scrollY:        '27vh',
					        scrollCollapse: true,
							/*
							 * processing: true, serverSide: true,
							 */
							ajax : {
								type : 'POST',
								url : "subjectList.do",
								contentType : 'application/json',
								dataType : 'json',
								data : {
									'csrfmiddlewaretoken' : $(
											"meta[name='_csrf']").attr(
											"content")
								}
							},
							columns : [ {
								data : "subjectId"
							}, {
								data : "classLevelType.board.boardName"
							}, {
								data : "classLevelType.className"
							}, {
								data : "subjectName"
							}, {
								data : "classLevelType.language"
							} ],
							select : true
						});
				new $.fn.dataTable.Buttons(table, [ {
					extend : "create",
					editor : editor
				}, {
					extend : "edit",
					editor : editor
				}, {
					extend : "remove",
					editor : editor
				} ]);

				table.buttons().container().appendTo(
						$('.col-sm-6:eq(0)', table.table().container()));
			}

		});
