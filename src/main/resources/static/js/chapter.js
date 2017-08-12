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
						url : 'chapterAction.do',
						submitSuccess : function(e, json, data) {
							debugger;
							alert(json);
							table.ajax.reload();
						},

						submitError : function(xhr, error, thrown) {
							console.log(xhr, error, thrown);
						}

					},
					idSrc : 'chapterId',
					table : "#chapterTable",
					fields : [ {
						label : "Chapter Id",
						name : "chapterId",
					},{
						label : "Board Name:",
						name : "subject.classLevelType.board.boardId",
						type : "select",
						options : boardsDropDownJSON
					},{
						label : "Class Level:",
						name : "subject.classLevelType.classLevel.classLevelId",
						type : "select",
						options : classLevelDropDownJSON
					},
					{
						label : "Class Name:",
						name : "subject.classLevelType.className",
						type : "select",
						options : classLevelTypeDropDownJSON
					},{
						label : "Language:",
						name : "subject.classLevelType.language",
						type : "select",
						options : [{"label":"Hindi", "value" : "Hindi"},
						           {"label": "English", "value":"English"}]
					}, {
						label : "Subject Name:",
						name : "subject.subjectName"
					}, {
						label : "Chapter Number:",
						name : "chapterNumber"
					},{
						label : "Chapter Name:",
						name : "chapterName"
					}, {
						label : "Status:",
						name : "status",
						type : "select",
						options : [{"label":"Pending", "value" : "Pending"},
						           {"label": "In-Progress", "value":"In-Progress"},
						           {"label":"Complete", "value" : "Complete"},
						           {"label":"Rewise", "value" : "Rewise"},
						           {"label":"Postponed", "value" : "Postponed"}]
					},{
						label : "Start Date:",
						type: 'date',
						name : "startDate"
					},{
						label : "Finished Date:",
						type: 'date',
						name : "finishDate"
					},{
						label : "Time Taken:",
						name : "timeTaken"
					},{
						label : "Taken By:",
						name : "users.username"
					}, ],
					submitSuccess : function(e, json, data) {
						debugger;
						alert(json);
						table.ajax.reload();
					},
				});
				
				var dataToSend = {
					action : "getAll"
				};
				table = $('#chapterTable').DataTable(
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
								url : "chapterList.do",
								contentType : 'application/json',
								dataType : 'json',
								data : {
									'csrfmiddlewaretoken' : $(
											"meta[name='_csrf']").attr(
											"content")
								}
							},
							columns : [ {
								data : "chapterId"
							}, {
								data : "subject.classLevelType.board.boardName"
							}, {
								data : "subject.classLevelType.className"
							}, {
								data : "subject.subjectName"
							}, {
								data : "subject.classLevelType.language"
							},{
								data : "chapterNumber"
							},{
								data : "chapterName"
							}, {
								data : "status"
							},{
								data : "startDate"
							},{
								data : "finishDate"
							},{
								data : "timeTaken"
							},{
								data : "users.username",
								sDefaultContent: ""
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
				
				editor.field( 'chapterId' ).disable();
				editor.field( 'subject.classLevelType.board.boardId' ).disable();
				editor.field( 'subject.classLevelType.classLevel.classLevelId' ).disable();
				editor.field( 'subject.classLevelType.className' ).disable();
				
			}
			
		});
