'use strict';

define([
  'angular'
], function(angular) {
  angular.module('ui.statesService', []).factory('States', function States($http) {
    var getStates = function () {
      return $http({method: 'GET', url: '/core/data/states.json'});
    };

    return {
      getStates:  getStates
    }
  });

});
