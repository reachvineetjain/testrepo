'use strict';

define([
  'angular'
], function(angular) {
  angular.module('ui.countriesService', []).factory('Countries', function Countries($http) {
    var getCountries = function () {
      return $http({
        method: 'GET',
        url: '/core/data/countries.json',
        cache: true
      });
    };

    return {
      getCountries:  getCountries
    }
  });

});
