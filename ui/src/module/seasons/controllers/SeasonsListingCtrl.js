(function(){
  var app = angular.module('ui.seasons');
	app.controller('SeasonsListingCtrl',['$http','$scope','$state','$stateParams',function($http,$scope,$state,$stateParams){
		$stateParams.programId;
		var seasons = this;
        seasons.listing = [];
        $http.get('/data/seasons-listing.json').success(function(data){
          $scope.programSeasonList = data;
        });
	}]);
})()
