var editor; // use a global for the submit and return data rendering in the
// examples
var table;
$(document).ready(
		function() {
			var token = $("meta[name='_csrf']").attr("content");
			$.ajaxSetup({
				beforeSend : function(xhr) {
					xhr.setRequestHeader('X-CSRF-TOKEN', token);
				}
			});
			editor = new $.fn.dataTable.Editor({
				ajax : {
					type : 'POST',
					contentType : 'application/json',
					data : function(d) {
						return JSON.stringify(d);
					},
					url : 'boardAction.do',

					submitSuccess : function(e,json, data) {
						alert(json);
						table.ajax.reload();
					},
					
					submitError : function(xhr, error, thrown) {
						console.log(xhr, error, thrown);
					}

				},
				idSrc:  'boardId',
				table : "#boardTable",
				fields : [ {
					label : "Board Id:",
					name : "boardId"
				}, {
					label : "Board Name:",
					name : "boardName"
				} ],
				submitSuccess : function(e,json, data) {
					alert(json);
					table.ajax.reload();
				},
			});

			var dataToSend = {
				action : "getAll"
			};
			table = $('#boardTable').DataTable(
					{
						lengthChange : true,
						/*processing: true,
				        serverSide: true,*/
						ajax : {
							type : 'POST',
							url : "boardList.do",
							contentType : 'application/json',
							dataType : 'json',
							data : {
								'csrfmiddlewaretoken' : $("meta[name='_csrf']")
										.attr("content")
							}
						},
						columns : [ {
							data : "boardId"
						}, {
							data : "boardName"
						}, ],
						select : true
					});

			// Display the buttons
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

		});
