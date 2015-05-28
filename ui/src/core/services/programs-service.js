'use strict';

define([
  'angular'
], function(angular) {
  angular.module('ui.programsService', []).factory('Programs', function Programs($http) {
    var getPrograms = function () {
      return $http({
        method: 'GET',
        url: '/core/data/programs.json',
        cache: true
      });
    };

    return {
      getPrograms:  getPrograms
    }
  });

});
