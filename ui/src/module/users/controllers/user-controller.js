'use strict';

define([
  'angular',
  '/core/services/departments-service.js',
  '/core/services/resource-groups-service.js',
  '/core/services/staff-roles-service.js',
  '/core/services/states-service.js',
  '/core/services/countries-service.js',
  '../directives/checkbox-group-directive.js'
], function (angular) {
  angular.module('ui.userManagement.controller', ['ui.departmentsService','ui.resourceGroupsService', 'ui.staffRolesService', 'ui.statesService', 'ui.countriesService', 'ui.userManagement.directive.checkboxGroup']).controller('UserCtrl', ['$scope', '$filter', 'Departments', 'ResourceGroups', 'StaffRoles', 'States', 'Countries', function($scope, $filter, Departments, ResourceGroups, StaffRoles, States, Countries) {
    $scope.user = {};
    $scope.programs = [];

    // Get list of resource groups
    ResourceGroups.getResourceGroups().then(function(response){
      $scope.resourceGroups = response.data;
    });

    // Get list of departments
    Departments.getDepartments().then(function(response){
      $scope.departments = response.data;
    });

    // Get list of staff roles
    StaffRoles.getRoles().then(function(response){
      $scope.roles = response.data;
    });

    // Get list of states
    States.getStates().then(function(response){
      $scope.states = response.data['ns3.States'].states;
    });

    // Get list of countries
    Countries.getCountries().then(function(response){
      $scope.countries = response.data['ns1.Countries'].countries;
    });

    // When Department is selected, populate the Program and Functions drop-downs
    $scope.$watch('user.department', function(newValue) {
      if (!!newValue) {
        console.log($scope.departments);
        var selectedDepartment = $filter('filter')($scope.departments, {id: newValue})[0];
        $scope.programs = selectedDepartment.DepartmentPrograms;
        console.log($scope.programs);
        $scope.functions = selectedDepartment.CCI_Departmental_Functions;
      }
    });
  }]);
});
