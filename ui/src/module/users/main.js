'use strict';

define([
  'angular',
  './main.js',
  './controllers/user-controller.js'
], function(angular) {
  angular.module('ui').requires.push('ui.userManagement');

  return angular.module('ui.userManagement', [
    'ui.select',
    'ui.userManagement.controller'
  ]).
    config(['$stateProvider','$urlRouterProvider', 'uiSelectConfig',function($stateProvider, $urlRouterProvider, uiSelectConfig) {
      'use strict';

      $urlRouterProvider
        .otherwise('/new-user');

      $stateProvider
        .state('newUser',{
          url:'/new-user',
          templateUrl: 'templates/new-user.html',
          controller: 'UserCtrl'
        })
        .state('editUser',{
          url:'/edit-user/:userId',
          templateUrl: 'templates/new-user.html',
          controller: 'UserCtrl'
        });

      uiSelectConfig.theme = '/core/directives/go-select';
    }]);
});
