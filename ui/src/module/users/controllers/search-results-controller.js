'use strict';
define([
  'angular',
  '/core/services/users-service.js'
], function(angular){
  angular
    .module('ui.userManagement.controller.search-results-controller',['ui.usersService'])
    .controller('SearchResultsCtrl',['$scope','$state','Users',function($scope,$state,Users){
      console.log('search results controller');

      // Get list of users
      Users.getAllUsers().then(function(response) {
        $scope.users = response.data['ns1.CCIUsers'].cciUsers;
      });
    }]);
});
