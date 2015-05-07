'use strict';

define([
  'angular',
  '/core/services/departments-service.js',
  '/core/services/resource-groups-service.js'
], function (angular) {
  angular.module('ui.userManagement.controller', ['ui.departmentsService','ui.resourceGroupsService']).controller('UserCtrl', ['$scope', 'Departments', 'ResourceGroups', function($scope, Departments, ResourceGroups) {
    $scope.user = {};

    ResourceGroups.getResourceGroups().then(function(response){
      $scope.resourceGroups = response.data;
    });

    Departments.getDepartments().then(function(response){
      $scope.departments = response.data;
    });
  }]);
});
