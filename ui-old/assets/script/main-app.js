(function(){
	var app = angular.module('cciApp',['ui.router']);
	app.config(['$stateProvider','$urlRouterProvider',function($stateProvider, $urlRouterProvider) {
		
		$urlRouterProvider
			.otherwise('/home');

		$stateProvider
			.state('home',{
				url:'/home',
				views: {
				    "programListView": {
				        templateUrl: 'templates/programs-list.html',
				        controller: 'ProgramListingCtrl'
				    },
				    "seasonsListView": {
				    	templateUrl: "templates/seasons-list.html",
				    	controller: 'SeasonsListingCtrl'
				    },
				    "seasonsDetailView": {
				   		templateUrl: "templates/seasons-detail.html",
				   		controller: 'SeasonDetailsCtrl'
				    }
				}    
			})
			.state('program',{
				url:'/program/:programId',
				views: {
					"programListView": {
				        templateUrl: 'templates/programs-list.html',
				        controller: 'ProgramListingCtrl'
				    },
					"seasonsListView" : {
						templateUrl: "templates/seasons-list.html",
						controller: 'SeasonsListingCtrl'
					}
				}
			});
	}]);
})();