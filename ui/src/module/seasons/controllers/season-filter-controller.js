'use strict';
define(['angular'], function(angular){
	angular
		.module('ui.seasons.controller.season-filter-controller',[])
		.controller('SeasonFilterCtrl',['$scope','$state',function($scope, $state){
			console.log('--> Inside Season Filter Controller');
			$scope.$parent.launchScreen = false;
			$scope.$parent.baseLayout = 'layout-h';
			
			$scope.users = [{name: "Moroni", age: 50},
                {name: "Tiancum", age: 43},
                {name: "Jacob", age: 27},
                {name: "Nephi", age: 29},
                {name: "Enos", age: 34}];
			
		}]);
});