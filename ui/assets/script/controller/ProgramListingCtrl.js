(function(){
	var app = angular.module('cciApp');
	app.controller('ProgramListingCtrl',['$http','$scope','$state',function($http,$scope,$state){
		var programs = this;
        programs.listing = [];
        $http.get('././data/programs-listing.json').success(function(data){
          $scope.programsList = data;
        })

        console.log($state); // returns true
	}])
})();