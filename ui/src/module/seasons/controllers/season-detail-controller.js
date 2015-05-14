'use strict';
define([
  'angular',
  '/module/seasons/services/season-detail-service.js'
],function(angular){
  angular
    .module('ui.seasons.controller.season-detail-controller',['ui.seasons.services.season-detail-service'])
    .controller('SeasonDetailCtrl',['$http','$scope','$state','$stateParams','SeasonDetailService',
      function($http, $scope, $state, $stateParams, SeasonDetailService){
        var seasonId =  $stateParams.seasonId;
        SeasonDetailService.gettingSeasonDetail(seasonId).then(function(response){
          var result = response.data[0];
          $scope.seasonDetail = result;
          if(seasonId != $scope.seasonDetail.seasonProgramID){
            $scope.seasonDetail = [];
          }
        });
      }]);
});
