'use strict';
define([
    'angular'
], function(angular){
	angular
		.module('ui.seasons.controller.new-season-controller',[])
		.controller('NewSeasonCtrl',['$scope','$state',function($scope,$state){
			//$scope.$parent.showNewSeasonPane = true;
			console.log('---> Inside New Season Controller '+$scope.showNewSeasonPane);
			if($state.is('home.newSeason')){
				console.log('Current state is New Season');
				$scope.$parent.launchScreen = true;
			}
			else{
				$scope.$parent.launchScreen = false;
			}
			$scope.seasonCreationForm = function(){
				console.log('--> Validating Season Creation');
				if ($scope.createSeasonForm.$valid) {
					console.log('Forms has been validated successfully.');
					//$scope.
				}
			}
		}]);
});