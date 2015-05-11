'use strict';
define([
    'angular'
],function(angular){
    angular
        .module('ui.seasons.services.season-list-service',[])
        .factory('SeasonListService', function SeasonServices($http){
          return {
            gettingSeasonsList: gettingSeasonsList
          };

          function gettingSeasonsList(programId){
            return $http.get('/module/data/seasons-listing.json')
              .success(successSeasonsList)
              .error(failureSeasonsList);

            function successSeasonsList(data){
              var result = data[0].seasonList;
              return result;
            }

            function failureSeasonsList(error){
              console.log('XHR Failed for gettingProgramList. '+error.data);
            }
          }
        });
});
