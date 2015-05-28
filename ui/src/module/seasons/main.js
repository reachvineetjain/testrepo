'use strict';

define([
    'angular',
    './main.js',
    './controllers/three-pane-controller.js',
		'./controllers/new-season-controller.js',
		'./controllers/season-filter-controller.js',
    './controllers/program-list-controller.js',
    './controllers/season-list-controller.js',
    './controllers/season-detail-controller.js'
],  function(angular){
    angular.module('ui').requires.push('ui.seasons');
    return angular.module('ui.seasons',[
        'ui.seasons.services.program-list-service',
        'ui.seasons.services.season-list-service',
        'ui.seasons.controller.three-pane-controller',
				'ui.seasons.controller.new-season-controller',
				'ui.seasons.controller.season-filter-controller',
        'ui.seasons.controller.program-list-controller',
        'ui.seasons.controller.season-list-controller',
        'ui.seasons.controller.season-detail-controller'])
        .config(['$stateProvider','$urlRouterProvider',function($stateProvider, $urlRouterProvider){
            'use strict';
            $urlRouterProvider
                .otherwise('/home');

            $stateProvider
								.state('home',{
                    url: '/home',
                    views: {
                        'splitPaneView':{
                            templateUrl: 'templates/split-pane.html',
                            controller: 'ThreePaneCtrl'
                        }
                    }
                })
								.state('home.seasonSelect',{
									url: '/selectSeason',
									views: {
											'seasonSelectView':{
												templateUrl: 'templates/season-selection.html'
											}
									}
								})
								.state('home.newSeason',{
										url: '/newSeason',
										views: {
												'newSeasonView':{
													templateUrl: 'templates/new-season.html',
													controller: 'NewSeasonCtrl'
												}
										}
								})
								.state('home.seasonSearch',{
										url:'/filter',
										sticky: true,
										views:{
											'seasonsFilterView':{
													templateUrl: 'templates/season-filter.html',
													controller: 'SeasonFilterCtrl'
											}
										}
								})
                .state('home.program',{
                    url:'/program/:programId',
										sticky: true,
										views: {
                        'programListView':{
                            templateUrl: 'templates/programs-list.html',
                            controller: 'ProgramListingCtrl'
                        }
                    }
                })
                .state('home.season',{
                    url:'/program/:programId/season',
										sticky: true,
                    views:{
                       'seasonsListView':{
                            templateUrl: 'templates/seasons-list.html',
                            controller: 'SeasonsListingCtrl'
                        },
                        'seasonsDetailView':{
                            template: '<div class="no-selection"> Please select a season</div>'
                        }
                    }
                })
                .state('home.seasonsDetail',{
                    url:'/program/:programId/season/:seasonId',
                    views:{
                       'seasonsDetailView':{
                            templateUrl: 'templates/seasons-detail.html',
                            controller: 'SeasonDetailCtrl'
                        }
                    }
                });
        }]);
});
