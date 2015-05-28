'use strict';
define([
    'angular'
], function(angular){
    angular
        .module('ui.seasons.controller.three-pane-controller',[])
        .controller('ThreePaneCtrl',['$http','$scope','$state',function($http, $scope, $state){
            $scope.countElem = 1;
            $scope.programsList = [];
						$scope.launchScreen = true;
					  //$scope.showNewSeasonPane = false;
						console.log('---> Inside Three Pane Controller '+$scope.showNewSeasonPane);
						$scope.baseLayout = "layout-a";
						if($scope.launchScreen){
							$scope.launchPageTitle = "New Season";
							$state.go('home.seasonSelect');
						}
						else{
							$state.go('home.program');
						}
					
						
						
            
        }]);
});
// TBD: There should be "link:" function which would essentially handle manipulation of the CSS based on the user-agent for handling the different layouts.