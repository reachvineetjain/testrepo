'use strict';
define([
  'angular',
  '/core/services/departments-service.js',
  '/core/services/programs-service.js',
  '/core/services/staff-roles-service.js',
  '/core/services/countries-service.js'
], function(angular){
  angular
    .module('ui.userManagement.controller.search-criteria-controller',['ui.departmentsService','ui.programsService','ui.staffRolesService'])
    .controller('SearchCriteriaCtrl',['$scope','$state','Departments', 'Programs','StaffRoles','Countries',function($scope,$state,Departments,Programs,StaffRoles,Countries){
      $state.go('search.users');
      $scope.searchCriteria = {};

      // Get list of departments
      Departments.getDepartments().then(function(response){
        $scope.departments = response.data;
      });

      // Get list of programs
      Programs.getPrograms().then(function(response){
        $scope.programs = response.data['ns3.Programs'].programs;
      });

      // Get list of staff roles
      StaffRoles.getRoles().then(function(response){
        $scope.roles = response.data;
      });

      // Get list of countries
      Countries.getCountries().then(function(response){
        $scope.countries = response.data['ns1.Countries'].countries;
      });
    }]);
});
