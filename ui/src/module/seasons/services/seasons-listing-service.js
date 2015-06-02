'use strict';
define([
    'angular'
],function(angular){
    angular
        .module('ui.seasons.services.seasons-listing-service',[])
        .factory('SeasonsListingService', function SeasonServices($http){
          return {
            gettingSeasonsList: gettingSeasonsList
          };

          function gettingSeasonsList(){
            return $http.get('/module/data/all-seasons-list.json')
              .success(successSeasonsList)
              .error(failureSeasonsList);

            function successSeasonsList(data){
              var result = data[0];
              return result;
            }

            function failureSeasonsList(error){
              console.log('XHR Failed for gettingProgramList. '+error.data);
            }
          }
        });
});
