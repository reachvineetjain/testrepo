'use strict';

define([
  'angular'
], function(angular) {
  angular.module('ui.staffRolesService', []).factory('StaffRoles', function ResourceGroups($http) {
    var getRoles = function () {
      return $http({
        method: 'GET',
        url: '/core/data/staffRoles.json',
        cache: true
      });
    };

    return {
      getRoles:  getRoles
    }
  });

});
