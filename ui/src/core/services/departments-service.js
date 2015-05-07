'use strict';

define([
  'angular'
], function(angular) {
  angular.module('ui.departmentsService', []).factory('Departments', function Departments($http) {
    var getDepartments = function () {
      return $http({method: 'GET', url: '/core/data/departments.json'});
    };

    return {
      getDepartments:  getDepartments
    }
  });

});
