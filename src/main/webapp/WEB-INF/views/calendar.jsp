<div class="ct-site--map ct-u-backgroundGradient">
    <div class="container">
        <div class="ct-u-displayTableVertical text-capitalize">
            <div class="ct-u-displayTableCell">
                <span class="ct-u-textBig">
                    Event Management
                </span>
            </div>
            <div class="ct-u-displayTableCell text-right">
                <span class="ct-u-textNormal ct-u-textItalic">
                    <a href="index.html">Admin</a> / <a href="calendar.do">Event Management</a>
                </span>
            </div>
        </div>
    </div>
</div>
<div id="main" style="overflow: hidden;">
	<div class="site-content">
		<div class="sub-content-container">
			<div class="sub-content">
				<div id="calendar" data-calendar="calendar"></div>
				<div class="modal modal-fade in" id="event-modal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">x</span> <span class="sr-only">Close</span>
								</button>
								<h3 class="modal-title">Event</h3>
							</div>
							<div class="modal-body">
								<input type="hidden" name="event-index" value="">
								<form class="form-horizontal">
									<div class="form-group">
										<label for="min-date" class="col-sm-4 control-label">Name</label>
										<div class="col-sm-7">
											<input name="event-name" type="text" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label for="min-date" class="col-sm-4 control-label">Location</label>
										<div class="col-sm-7">
											<input name="event-location" type="text" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label for="min-date" class="col-sm-4 control-label">Dates</label>
										<div class="col-sm-7">
											<div class="input-group input-daterange"
												data-provide="datepicker">
												<input name="event-start-date" type="text"
													class="form-control" value="2012-04-05"> <span
													class="input-group-addon">to</span> <input
													name="event-end-date" type="text" class="form-control"
													value="2012-04-19">
											</div>
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
								<button type="button" class="btn btn-primary" id="save-event">
									Save</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	<div class="site-content"></div>
</div>

<script>
	var currentYear = new Date().getFullYear();
	var oldOptions;
	$('#calendar')
			.calendar(
					{
						enableContextMenu : true,
						enableRangeSelection : true,
						displayWeekNumber : true,
						contextMenuItems : [ {
							text : 'Update',
							click : editEvent
						}, {
							text : 'Delete',
							click : deleteEvent
						} ],
						selectRange : function(e) {
							editEvent({
								startDate : e.startDate,
								endDate : e.endDate
							});
						},
						mouseOnDay : function(e) {
							if (e.events.length > 0) {
								var content = '';

								for ( var i in e.events) {
									content += '<div class="event-tooltip-content">'
											+ '<div class="event-name" style="color:' + e.events[i].color + '">'
											+ e.events[i].name
											+ '</div>'
											+ '<div class="event-location">'
											+ e.events[i].location
											+ '</div>'
											+ '</div>';
								}

								$(e.element).popover({
									trigger : 'manual',
									container : 'body',
									html : true,
									content : content
								});

								$(e.element).popover('show');
							}
						},
						mouseOutDay : function(e) {
							if (e.events.length > 0) {
								$(e.element).popover('hide');
							}
						},
						dayContextMenu : function(e) {
							$(e.element).popover('hide');
						},
						dataSource : [ {
							id : 0,
							name : 'Google I/O',
							location : 'San Francisco, CA',
							startDate : new Date(currentYear, 4, 28),
							endDate : new Date(currentYear, 4, 29)
						}, {
							id : 1,
							name : 'Microsoft Convergence',
							location : 'New Orleans, LA',
							startDate : new Date(currentYear, 2, 16),
							endDate : new Date(currentYear, 2, 19)
						}, {
							id : 2,
							name : 'Microsoft Build Developer Conference',
							location : 'San Francisco, CA',
							startDate : new Date(currentYear, 3, 29),
							endDate : new Date(currentYear, 4, 1)
						}, {
							id : 3,
							name : 'Apple Special Event',
							location : 'San Francisco, CA',
							startDate : new Date(currentYear, 8, 1),
							endDate : new Date(currentYear, 8, 1)
						}, {
							id : 4,
							name : 'Apple Keynote',
							location : 'San Francisco, CA',
							startDate : new Date(currentYear, 8, 9),
							endDate : new Date(currentYear, 8, 9)
						}, {
							id : 5,
							name : 'Chrome Developer Summit',
							location : 'Mountain View, CA',
							startDate : new Date(currentYear, 10, 17),
							endDate : new Date(currentYear, 10, 18)
						}, {
							id : 6,
							name : 'F8 2015',
							location : 'San Francisco, CA',
							startDate : new Date(currentYear, 2, 25),
							endDate : new Date(currentYear, 2, 26)
						}, {
							id : 7,
							name : 'Yahoo Mobile Developer Conference',
							location : 'New York',
							startDate : new Date(currentYear, 7, 25),
							endDate : new Date(currentYear, 7, 26)
						}, {
							id : 8,
							name : 'Android Developer Conference',
							location : 'Santa Clara, CA',
							startDate : new Date(currentYear, 11, 1),
							endDate : new Date(currentYear, 11, 4)
						}, {
							id : 9,
							name : 'LA Tech Summit',
							location : 'Los Angeles, CA',
							startDate : new Date(currentYear, 10, 17),
							endDate : new Date(currentYear, 10, 17)
						} ]
					});

	$('#save-event').click(function() {
		saveEvent();
	});
</script>
