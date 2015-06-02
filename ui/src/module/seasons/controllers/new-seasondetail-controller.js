'use strict';
define([
  'angular'//,
  //'/module/seasons/services/season-list-service.js'
], function(angular){
    angular
        .module('ui.seasons.controller.new-seasondetail-controller',[])
				.filter('seasonById',function(){
					return function(input, id){
						for(var i=0; i<input.length; i++){
							if(input[i].id == id){
								return input[i];
							}
						}
						return null;
					}
				})
        .controller('NewSeasonDetailCtrl',
        ['$http','$scope','$state','$stateParams',
          function($http,$scope,$state,$stateParams){
						$scope.$parent.baseLayout = "layout-a";
						
						$scope.getSeasonDetailById = function(seasonId){
							var found = $
						}
        }]);
});
