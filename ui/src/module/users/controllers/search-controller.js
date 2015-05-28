'use strict';
define([
  'angular'
], function(angular){
  angular
    .module('ui.userManagement.controller.search-controller',[])
    .controller('SearchCtrl',['$http','$scope','$state',function($http, $scope, $state){
      console.log('search controller');
    }]);
});
