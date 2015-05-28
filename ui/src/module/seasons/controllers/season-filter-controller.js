'use strict';
define(['angular'], function(angular){
	angular
		.module('ui.seasons.controller.season-filter-controller',[])
		.controller('SeasonFilterCtrl',['$scope','$state',function($scope, $state){
			console.log('--> Inside Season Filter Controller');
			$scope.$parent.launchScreen = false;
			$scope.$parent.baseLayout = 'layout-b';
		}]);
});