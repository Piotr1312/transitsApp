app.controller('TransitController', function($http) {
    var current = this;
 
    current.addTransit = function(transit) {
        $http({
            method : 'POST',
            url : 'api/transits',
            data : transit
        }).then(function success(response) {
        	current.getLastTransits();
            current.transit = {};
        }, function error(response) {
            console.log('Add transit error ' + transit);
        });
    }
    
    current.getTodayReport = function() {
    	$http({
    		method: 'GET',
    		url: 'api/reports/daily'
    	}).then(function success(response) {
    		current.dailyReport = response.data;
    		current.dailyReportTitle = 'Today report';
    	}, function error(response) {
    		console.log('Get daily report error ' + response.status);
    	});
    }
    
    current.getDailyReport = function(dailyReport) {
    	$http({
    		method: 'GET',
    		url: 'api/reports/daily',
    		params: {date: dailyReport.date}
    	}).then(function success(response) {
    		current.dailyReport = response.data;
    		current.dailyReportTitle = 'Report from ' + dailyReport.date;
    	}, function error(response) {
    		console.log('Get daily report error ' + response.status);
    	});
    }
    
    current.getCurrentMonthReport = function() {
    	$http({
    		method: 'GET',
    		url: 'api/reports/periodic'
    	}).then(function success(response) {
    		current.periodicReport = response.data;
    		current.periodicReportTitle = 'Current month report';
    	}, function error(response) {
    		console.log('Get periodic report error ' + response.status);
    	});
    }
    
    current.getPeriodicReport = function(periodicReport) {
    	$http({
    		method: 'GET',
    		url: 'api/reports/periodic',
    		params: {start_date: periodicReport.startDate,
    				 end_date: periodicReport.endDate}
    	}).then(function success(response) {
    		current.periodicReport = response.data;
    		current.periodicReportTitle = 'Report from period ' + periodicReport.startDate + ' - ' + periodicReport.endDate;
    	}, function error(response) {
    		console.log('Get periodic report error ' + response.status);
    	});
    }
    
    current.getLastTransits = function() {
    	$http({
    		method: 'GET',
    		url: 'api/transits/last10',
    	}).then(function success(response) {
    		current.transits = response.data;
    		current.transitsTitle = 'Last transits';
    	}, function error(response) {
    		console.log('Get last transits error ' + response.status);
    	});
    }
    
    current.getTransits = function(period) {
    	$http({
    		method: 'GET',
    		url: 'api/transits/periodic',
    		params: {start_date: period.startDate,
    				 end_date: period.endDate}
    	}).then(function success(response) {
    		current.transits = response.data;
    		current.transitsTitle = 'Transits in period ' + period.startDate + ' - ' + period.endDate;
    	}, function error(response) {
    		console.log('Get transits error ' + response.status);
    	});
    }
    
    current.getLastTransits();
    current.getTodayReport();
    current.getCurrentMonthReport();
});
