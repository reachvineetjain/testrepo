'use strict';
define(['angular','/module/seasons/services/seasons-listing-service.js'], function(angular){
	angular
		.module('ui.seasons.controller.filter-result-controller',['ui.seasons.services.seasons-listing-service'])
		.controller('FilterResultCtrl',['$scope','$state','SeasonsListingService',function($scope, $state,SeasonsListingService){
			console.log('--> Inside Filter Result Controller');
			$scope.$parent.baseLayout = 'layout-h';
			
			$scope.tableViewListing = 'true'; 
			if($state.is('home.detail')){
				$scope.tableViewListing = 'false';
			}
			
			SeasonsListingService.gettingSeasonsList().then(function(response){
				var result = response.data;
				if(result.length > 0 ){
					$scope.RSeasonsList = result;
				}
			});
		}]);
});