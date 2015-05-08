'use strict';
define([
    'angular'
], function(angular){
    angular
        .module('ui.seasons.controller.season-list-controller',[])
        .controller('SeasonsListingCtrl',['$http','$scope','$state','$stateParams',function($http,$scope,$state,$stateParams){
            var seasons = this;
            seasons.listing = [];
        }]);
});