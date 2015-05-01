
angular.module('ui', [
  'ngRoute',
  'ui.todo'
])
.config(function ($routeProvider) {
  'use strict';
  $routeProvider
    .when('/todo', {
      controller: 'TodoCtrl',
      templateUrl: '/ui/todo/todo.html'
    })
    .otherwise({
      redirectTo: '/todo'
    });
});
