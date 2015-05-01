(function(){
  var app = angular.module('ui', [
    'ui.router',
    'ui.todo',
    'ui.seasons'
  ]);
  app.config(['$stateProvider','$urlRouterProvider',function($stateProvider, $urlRouterProvider) {
    'use strict';
  	$urlRouterProvider
  		.otherwise('/home');

  	$stateProvider
  		.state('home',{
  			url:'/home',
  			views: {
          "programListView": {
            templateUrl: 'seasons/templates/programs-list.html',
            controller: 'ProgramListingCtrl'
          },
          "seasonsListView": {
            templateUrl: "seasons/templates/seasons-list.html",
            controller: 'SeasonsListingCtrl'
          },
          "seasonsDetailView": {
            templateUrl: "seasons/templates/seasons-detail.html",
            controller: 'SeasonDetailsCtrl'
          }
  			}
  		})
  		.state('program',{
  			url:'/program/:programId',
  			views: {
  				"programListView": {
  	        templateUrl: 'seasons/templates/programs-list.html',
  	        controller: 'ProgramListingCtrl'
  		    },
  				"seasonsListView" : {
  					templateUrl: "seasons/templates/seasons-list.html",
  					controller: 'SeasonsListingCtrl'
  				}
  			}
  		});
  }]);
})();
