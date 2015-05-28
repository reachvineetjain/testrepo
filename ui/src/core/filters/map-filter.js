'use strict';

define([
  'angular'
], function(angular) {
  angular.module('ui.filter.mapFilter', []).filter('map', function() {
    return function(input, propName) {
      if (!!input) {
        return input.map(function(item) {
          return item[propName];
        });
      }
    };
  });

});

