'use strict';

define([
  'angular'
], function(angular) {
  angular.module('ui.usersService', []).factory('Users', function States($http) {
    var getAllUsers = function () {
      return $http({method: 'GET', url: '/core/data/allUsers.json'});
    };

    var getUserById = function () {
      return $http({method: 'GET', url: '/core/data/userDetails.json'})
    }

    return {
      getAllUsers:  getAllUsers,
      getUserById: getUserById
    }
  });

});
