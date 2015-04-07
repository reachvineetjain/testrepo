'use strict';

/**
 * CustomerController
 * 
 * @constructor
 */

var app = angular.module('myApp', []);
    app.controller('SeasonsController', function($scope, $http) {
        //use proper path system here, we can't use local host
      $http.get("http://localhost:8080/cci_gh_go/services/season/list")
      .success(function (response) {
          $scope.seasonProgramJsonData = response.SeasonSearchResponse.seasonPrograms;
       });
   });