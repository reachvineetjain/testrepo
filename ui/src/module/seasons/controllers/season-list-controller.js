'use strict';
define([
    'angular',
  '/module/seasons/services/season-list-service.js'
], function(angular){
    angular
        .module('ui.seasons.controller.season-list-controller',['ui.seasons.services.season-list-service'])
        .controller('SeasonsListingCtrl',
        ['$http','$scope','$state','$stateParams','SeasonListService',
          function($http,$scope,$state,$stateParams,SeasonListService){
            var programId = $stateParams.programId;
            SeasonListService.gettingSeasonsList($stateParams.programId).then(function(response) {
              var result = response.data[0].seasonList;
              $scope.seasonsList = result;
            });
            //seasons.listing = [];
        }]);
});
