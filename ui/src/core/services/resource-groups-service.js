'use strict';

define([
  'angular'
], function(angular) {
  angular.module('ui.resourceGroupsService', []).factory('ResourceGroups', function ResourceGroups($http) {
    var getResourceGroups = function () {
      return $http({
        method: 'GET',
        url: '/core/data/resourceGroups.json',
        cache: true
      });
    };

    return {
      getResourceGroups:  getResourceGroups
    }
  });

});
