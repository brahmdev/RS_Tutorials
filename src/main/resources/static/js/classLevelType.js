var editor; // use a global for the submit and return data rendering in the
// examples
var table;
var boardsDropDownJSON = null;
var classLevelDropDownJSON = null;
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
					if (boardsDropDownJSON != null
							&& classLevelDropDownJSON != null) {
						formTableNEditor();
					}
				},
			});

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
					if (boardsDropDownJSON != null
							&& classLevelDropDownJSON != null) {
						formTableNEditor();
					}
				},
				type : 'POST'
			});

			function formTableNEditor() {
				editor = new $.fn.dataTable.Editor({
					ajax : {
						type : 'POST',
						contentType : 'application/json',
						data : function(d) {
							return JSON.stringify(d);
						},
						url : 'classLevelTypeAction.do',

						submitSuccess : function(e, json, data) {
							alert(json);
							table.ajax.reload();
						},

						submitError : function(xhr, error, thrown) {
							console.log(xhr, error, thrown);
						}

					},
					idSrc : 'classLevelTypeId',
					table : "#classLevelTypeTable",
					fields : [ {
						label : "Class Level Id",
						name : "classLevelTypeId"
					}, {
						label : "Class Name:",
						name : "className"
					},/*
						 * { label : "Class Level:", name :
						 * "classLevel.description", type : "select" },
						 */{
						label : "Board Name:",
						name : "board.boardId",
						type : "select",
						options : boardsDropDownJSON
					}, {
						label : "Type:",
						name : "classLevel.classLevelId",
						type : "select",
						options : classLevelDropDownJSON
					},{
						label : "Language:",
						name : "language",
						type : "select",
						options : [{"label":"Hindi", "value" : "Hindi"},
						           {"label": "English", "value":"English"}]
					}, {
						label : "Fees:",
						name : "fees"
					}, ],
					submitSuccess : function(e, json, data) {
						alert(json);
						table.ajax.reload();
					},
				});
				var dataToSend = {
					action : "getAll"
				};
				table = $('#classLevelTypeTable').DataTable(
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
								url : "classLevelTypeList.do",
								contentType : 'application/json',
								dataType : 'json',
								data : {
									'csrfmiddlewaretoken' : $(
											"meta[name='_csrf']").attr(
											"content")
								}
							},
							columns : [ {
								data : "classLevelTypeId"
							}, {
								data : "className"
							}, {
								data : "board.boardName"
							}, {
								data : "classLevel.description"
							}, {
								data : "language"
							}, {
								data : "fees"
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
