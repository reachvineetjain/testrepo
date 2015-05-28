'use strict';
define([
  'angular',
  '/module/seasons/services/season-detail-service.js'
],function(angular){
  angular
    .module('ui.seasons.controller.season-detail-controller',['ui.seasons.services.season-detail-service'])
    .controller('SeasonDetailCtrl',['$http','$scope','$state','$stateParams','SeasonDetailService','$filter',
      function($http, $scope, $state, $stateParams, SeasonDetailService,$filter){
        var seasonId =  $stateParams.seasonId;
				$scope.selectedProgram = $stateParams.programId;
				// This "seasonId" is required to fetch the details of each of the Season. 
        SeasonDetailService.gettingSeasonDetail(seasonId).then(function(response){
          var result = response.data[0];
          $scope.seasonDetail = result;
					$scope.seasonDetail.startDate = $filter('date')($scope.seasonDetail.startDate, "yyyy-MM-dd");
					$scope.seasonDetail.endDate = $filter('date')($scope.seasonDetail.endDate, "yyyy-MM-dd");
					if(seasonId != $scope.seasonDetail.seasonProgramID){
            $scope.seasonDetail = [];
          }
        });
      }]);
});
