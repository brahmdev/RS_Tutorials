var editor; // use a global for the submit and return data rendering in the
// examples
var table;
$(document).ready(
		function() {
			var token = $("meta[name='_csrf']").attr("content");
			$.ajaxSetup({
			    beforeSend: function(xhr) {
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
					url : 'createCountry.do'
				/*
				 * success : function(json) { success(json); }, error :
				 * function(xhr, error, thrown) { error(xhr, error, thrown); }
				 */
				},
				table : "#example",
				fields : [ {
					label : "Country Code:",
					name : "countryCode"
				}, {
					label : "Country Name:",
					name : "countryName"
				} ]
			});

			/*
			 * var editor = new $.fn.Editor( { table: "#example", ajax: function (
			 * method, url, data, success, error ) { $.ajax( { type: method,
			 * url: url, data: data, dataType: "json", success: function (json) {
			 * success( json ); }, error: function (xhr, error, thrown) { error(
			 * xhr, error, thrown ); } } ); } } );
			 */

			var dataToSend = {
				action : "getAll"
			};
			table = $('#example').DataTable(
					{
						lengthChange : true,
						ajax : {
							type : 'POST',
							url : "countryList.do",
							contentType : 'application/json',
							dataType : 'json',
							data : {
								'csrfmiddlewaretoken' : $("meta[name='_csrf']")
										.attr("content")
							}
						},
						columns : [ {
							data : "countryCode"
						}, {
							data : "countryName"
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

