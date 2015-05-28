'use strict';

define([
  'angular',
  './main.js',
  './controllers/user-controller.js',
  './controllers/search-controller.js',
  './controllers/search-criteria-controller.js',
  './controllers/search-results-controller.js',
  '/core/filters/map-filter.js',
], function(angular) {
  angular.module('ui').requires.push('ui.userManagement');

  return angular.module('ui.userManagement', [
    'ui.select',
    'ui.userManagement.controller.user',
    'ui.userManagement.controller.search-controller',
    'ui.userManagement.controller.search-criteria-controller',
    'ui.userManagement.controller.search-results-controller',
    'ui.filter.mapFilter'
  ]).
    config(['$stateProvider','$urlRouterProvider', 'uiSelectConfig',function($stateProvider, $urlRouterProvider) {
      'use strict';

      $urlRouterProvider
        .otherwise('/search/results');

      $stateProvider
        .state('search',{
          url: '/search',
          views: {
            'splitPaneView':{
              templateUrl: 'templates/split-pane.html',
              controller: 'SearchCtrl'
            }
          }
        })
        .state('search.all',{
          url:'/results',
          sticky: true,
          views: {
            'searchCriteriaView':{
              templateUrl: 'templates/user-search-criteria.html',
              controller: 'SearchCriteriaCtrl'
            }
          }
        })
        .state('search.users',{
          url:'/results/users',
          sticky: true,
          views:{
            'usersListView':{
              templateUrl: 'templates/user-search-result-list.html',
              controller: 'SearchResultsCtrl'
            },
            'userDetailView':{
              template: '<div class="no-selection"> Please select a user</div>'
            }
          }
        })
        .state('search.userDetail',{
          url:'/results/users/:userId',
          sticky: true,
          views:{
            'userDetailView':{
              templateUrl: 'templates/user-detail.html',
              controller: 'UserCtrl'
            }
          }
        })
        .state('new-user',{
          url:'/new-user',
          views:{
            'newUser':{
              templateUrl: 'templates/new-user.html',
              controller: 'UserCtrl'
            }
          }
        });
    }]);
});
