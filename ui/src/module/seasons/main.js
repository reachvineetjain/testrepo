'use strict';

define([
    'angular',
    './main.js',
    './controllers/three-pane-controller.js',
    './controllers/program-list-controller.js',
    './controllers/season-list-controller.js'
],  function(angular){
    angular.module('ui').requires.push('ui.seasons');
    return angular.module('ui.seasons',[
        'ui.seasons.services',
        'ui.seasons.controller.three-pane-controller',
        'ui.seasons.controller.program-list-controller',
        'ui.seasons.controller.season-list-controller'])
        .config(['$stateProvider','$urlRouterProvider',function($stateProvider, $urlRouterProvider){
            'use strict';
            $urlRouterProvider
                .otherwise('/home');

            $stateProvider
                .state('home',{
                    url: '/home',
                    views: {
                        'splitPaneView':{
                            templateUrl: 'templates/split-pane.html',
                            controller: 'ThreePaneCtrl'
                        }
                    }
                })
                .state('home.program',{
                    url:'/program/:programId',
                    views: {
                        'programListView':{
                            templateUrl: 'templates/programs-list.html',
                            controller: 'ProgramListingCtrl'
                        }
                    }
                })
                .state('home.seasonsList',{
                    url:'/program/:programId/season',
                    views:{
                        'seasonsListView':{
                            templateUrl: 'templates/seasons-list.html',
                            controller: 'SeasonsListingCtrl'
                        }
                    }
                });
                /*.state('home.seasonsDetail',{
                    url:'/program/:programId/season/:seasonId',
                    views:{
                        "seasonsDetail":{
                            templateUrl: 'templates/seasons-detail.html',
                            controller: 'SeasonsDetailCtrl'
                        }
                    }
                });*/
        }]);
});