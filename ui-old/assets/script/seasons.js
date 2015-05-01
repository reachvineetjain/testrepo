(function(){
  var app = angular.module('seasons-module',[]);

  app.directive("seasonsListing", function(){
    return {
      restrict: "E",
      templateUrl: "./templates/seasons-list.html",
      controller: ['$http',function($http){
        var seasons = this;
        seasons.listing = [];
        $http.get('./data/seasons-listing.json').success(function(data){
          seasons.listing = data;
        });
      }],
      controllerAs: "seasons"
    };
  });

  app.directive("seasonsDetail", function(){
      return{
        restrict: "E",
        templateUrl: "./templates/seasons-detail.html"
      }
  });
})();