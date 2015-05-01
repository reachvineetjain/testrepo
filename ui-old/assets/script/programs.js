(function(){
  var app = angular.module('programs-module',[]);

  app.directive("programsListing", function(){
    return {
      restrict: "E",
      templateUrl: "./templates/programs-list.html",
      controller: ['$http',function($http){
        var programs = this;
        programs.listing = [];
        $http.get('./data/programs-listing.json').success(function(data){
          programs.listing = data;
        });
      }],
      controllerAs: "programs"
    };
  });
})();